package com.atguigu.gulimall.order.service.impl;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.feign.CartFeign;
import com.atguigu.gulimall.order.feign.MemberFeign;
import com.atguigu.gulimall.order.feign.SkuFeign;
import com.atguigu.gulimall.order.feign.WareFeign;
import com.atguigu.gulimall.order.mapper.OrderMapper;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.MemberAddressVo;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import com.atguigu.gulimall.order.vo.OrderItem;
import com.atguigu.gulimall.order.vo.OrderSubmitVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import constant.OrderConstant;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import to.MemberEntityVo;
import to.SkuHasStockTo;
import to.cart.CartItem;
import utils.R;

/**
 * @author tifa
 * @description 针对表【oms_order(订单)】的数据库操作Service实现
 * @createDate 2025-05-08 21:18:35
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity>
    implements OrderService {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private MemberFeign memberFeign;

  @Autowired
  private CartFeign cartFeign;

  @Autowired
  private WareFeign wareFeign;
  @Autowired
  private SkuFeign skuFeign;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private ThreadPoolExecutor threadPoolExecutor;
  @Autowired
  private ObjectMapper objectMapper;


  @RabbitListener(queues = {"hello"})
  void listen(Message message, @Payload OrderEntity orderEntity, Channel channel)
      throws IOException {
    System.out.println("消费者" + orderEntity);
    long deliveryTag = message.getMessageProperties().getDeliveryTag();

    channel.basicAck(deliveryTag, false);
  }


  /**
   * 确认回调
   */
  @PostConstruct
  public void initRabbitTemplate() {
    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
      System.out.println("confirm..." + correlationData);
      System.out.println("ack..." + ack);
      System.out.println("失败原因..." + cause);
    });

//    当生产者的消息没有到达队列，才会回调这个
    rabbitTemplate.setReturnsCallback(new ReturnsCallback() {
      @Override
      public void returnedMessage(ReturnedMessage returned) {
        System.out.println(returned);
      }
    });

  }

  @Override
  public OrderConfirmVo confirmOrder(MemberEntityVo memberEntityVo) {
    OrderConfirmVo confirmVo = new OrderConfirmVo();
    CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
      List<MemberAddressVo> memberByMemberId = memberFeign.getMemberByMemberId(
          memberEntityVo.getId());
      confirmVo.setAddress(memberByMemberId);
    }, threadPoolExecutor);
    CompletableFuture<List<CartItem>> future2 = CompletableFuture.supplyAsync(
        () -> {
          //从购物车中找到当前用户的所有购物内容
          List<CartItem> cartItems = cartFeign.getCartItems(memberEntityVo.getId());
          //过滤剩下来被选中的商品。
          return cartItems.stream()
              .filter(CartItem::getChecked)
              .toList();
        }, threadPoolExecutor);

    //查询出来最新的价格信息
    var future3 = future2.thenApplyAsync(
        (checkedCartItems) -> skuFeign.skuInfoEntityList(checkedCartItems), threadPoolExecutor);

    // 查询出是否有库存
    var future4 = future2.thenApplyAsync((checkedCartItems) -> {
      R skuHasStock = wareFeign.getSkuHasStock(
          checkedCartItems.stream().map(CartItem::getSkuId).toList());
      return objectMapper.convertValue(skuHasStock.get("data"),
          new TypeReference<List<SkuHasStockTo>>() {
          });
    }, threadPoolExecutor);

    CompletableFuture.allOf(future1, future2, future3, future4).join();

    List<CartItem> join2 = future2.join();
    var orderItems = join2.stream().map(item -> {
      OrderItem orderItem = new OrderItem();
      BeanUtils.copyProperties(item, orderItem);
      return orderItem;
    }).toList();

    List<CartItem> join3 = future3.join();
    Map<Long, BigDecimal> cartItemMap = join3.stream()
        .collect(Collectors.toMap(CartItem::getSkuId, CartItem::getPrice));
    orderItems.forEach(orderItem ->
        orderItem.setPrice(cartItemMap.get(orderItem.getSkuId())));

    Map<Long, OrderItem> orderItemMapMap = orderItems.stream()
        .collect(Collectors.toMap(OrderItem::getSkuId, item -> item));

    List<SkuHasStockTo> join = future4.join();
    if (join != null) {
      for (var c : join) {
        Long skuId = c.getSkuId();
        Boolean hasStock = c.getHasStock();
        orderItemMapMap.get(skuId).setHasStock(hasStock);
      }
    }
    confirmVo.setItems(orderItems);
    Integer integration = memberEntityVo.getIntegration();
    confirmVo.setIntegration(integration);

    //防重令牌

    String orderKey = UUID.randomUUID().toString().replace("-", "");
    String token = UUID.randomUUID().toString().replace("-", "");
    stringRedisTemplate.opsForValue()
        .set(OrderConstant.USER_ORDER_TOKEN_PREFIX + orderKey, token, 10,
            TimeUnit.MINUTES);

    confirmVo.setToken(token);
    confirmVo.setOrderKey(orderKey);
    return confirmVo;
  }

  @Override
  public Boolean submit(OrderSubmitVo orderSubmitVo, MemberEntityVo memberEntityVo) {
    String orderToken = orderSubmitVo.getOrderToken();
    String token = orderSubmitVo.getToken();

    String script =
        "if redis.call('get', KEYS[1]) == ARGV[1] then " +
            "   return redis.call('del', KEYS[1]) " +
            "else " +
            "   return 0 " +
            "end";

    Long result = stringRedisTemplate.execute(
        new DefaultRedisScript<>(script, Long.class),
        Collections.singletonList(OrderConstant.USER_ORDER_TOKEN_PREFIX + orderToken),
        token
    );
    log.error("结果是{}",result);

    return 1L==result;
  }

}




