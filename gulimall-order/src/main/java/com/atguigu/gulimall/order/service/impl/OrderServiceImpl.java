package com.atguigu.gulimall.order.service.impl;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.entity.OrderItemEntity;
import com.atguigu.gulimall.order.feign.CartFeign;
import com.atguigu.gulimall.order.feign.MemberFeign;
import com.atguigu.gulimall.order.feign.SkuFeign;
import com.atguigu.gulimall.order.feign.SpuFeign;
import com.atguigu.gulimall.order.feign.WareFeign;
import com.atguigu.gulimall.order.mapper.OrderMapper;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.MemberAddressVo;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import com.atguigu.gulimall.order.vo.OrderItem;
import com.atguigu.gulimall.order.vo.OrderSubmitVo;
import com.atguigu.gulimall.order.vo.SpuInfoVo;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
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
  private SpuFeign spuFeign;


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


  private List<OrderItem> getHasStockSkuItem(Long memberId) {

    var cartItemsFuture = CompletableFuture.supplyAsync(
        () -> cartFeign.getCartItems(memberId).stream().filter(CartItem::getChecked).toList(),
        threadPoolExecutor);

    var skuHasFuture = cartItemsFuture.thenApplyAsync((cartItems) -> {
      R skuHasStock = wareFeign.getSkuHasStock(cartItems.stream().map(CartItem::getSkuId).toList());
      List<SkuHasStockTo> skuList = skuHasStock.getData(new TypeReference<>() {
      });

      return skuList.stream().filter(SkuHasStockTo::getHasStock)
          .collect(Collectors.toMap(SkuHasStockTo::getSkuId, s -> s));
    }, threadPoolExecutor);

    var newestCartItemsFuture = cartItemsFuture.thenApplyAsync(
        (cartItems -> skuFeign.skuInfoEntityList(cartItems)), threadPoolExecutor);
    // 合并库存信息和最新商品信息

    //注意这里只筛选出来了有货的
    return newestCartItemsFuture.thenCombine(skuHasFuture, (newestCartItems, skuIdMap) ->
        newestCartItems.stream()
            .filter(item -> skuIdMap.containsKey(item.getSkuId())
                && item.getCount() <= skuIdMap.get(item.getSkuId()).getSkuNums())
            .map(a -> {
              OrderItem orderItem = new OrderItem();
              BeanUtils.copyProperties(a, orderItem);
              orderItem.setHasStock(true);
              return orderItem;
            })
            .toList()
    ).join();
  }


  @Override
  public OrderConfirmVo confirmOrder(MemberEntityVo memberEntityVo) {
    OrderConfirmVo confirmVo = new OrderConfirmVo();
    var future1 = CompletableFuture.runAsync(() -> {
      List<MemberAddressVo> memberByMemberId = memberFeign.getMemberByMemberId(
          memberEntityVo.getId());
      confirmVo.setAddress(memberByMemberId);
    }, threadPoolExecutor);

    var future2 = CompletableFuture.supplyAsync(() -> getHasStockSkuItem(memberEntityVo.getId()),
        threadPoolExecutor);
    CompletableFuture.allOf(future1, future2);
    confirmVo.setItems(future2.join());
    //积分
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

    //开始创建订单
    String orderId = IdWorker.getTimeId();
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOrderSn(orderId);
    orderEntity.setMemberId(memberEntityVo.getId());
    orderEntity.setMemberUsername(memberEntityVo.getUsername());

    orderEntity.setFreightAmount(BigDecimal.ZERO);
    orderEntity.setPayType(orderSubmitVo.getPayType());
    //等待付款
    orderEntity.setStatus(0);
    //确认收货状态
    orderEntity.setConfirmStatus(0);
    //未删除
    orderEntity.setDeleteStatus(0);


    //从购物车中获得最新的价格数据
    var OrderItemFuture = CompletableFuture.supplyAsync(
        () -> getHasStockSkuItem(memberEntityVo.getId()), threadPoolExecutor);

    var addFuture = CompletableFuture.runAsync(() -> {
      R addressData = memberFeign.getAddressById(memberEntityVo.getId());
      MemberAddressVo addrEntity = addressData.getData(new TypeReference<>() {
      });
      orderEntity.setReceiverName(addrEntity.getName());
      orderEntity.setReceiverPhone(addrEntity.getPhone());
      orderEntity.setReceiverPostCode(addrEntity.getPostCode());
      orderEntity.setReceiverProvince(addrEntity.getProvince());
      orderEntity.setReceiverCity(addrEntity.getCity());
      orderEntity.setReceiverRegion(addrEntity.getRegion());
      orderEntity.setReceiverDetailAddress(addrEntity.getDetailAddress());
    }, threadPoolExecutor);

    CompletableFuture.allOf(addFuture, OrderItemFuture).join();
    List<OrderItem> join = OrderItemFuture.join();

    orderEntity.setTotalAmount(
        join.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

    //生成订单具体项
    List<OrderItemEntity> orderItemEntities = getOrderItem(join, orderEntity);

    //校验价格
    int change = computePrice(orderSubmitVo, orderItemEntities);

    log.info("订单的数据{}", orderEntity);
    return 1L == result;
  }


  //验价，对比前端传过来的价格和最终价格是否一致
  private int computePrice(OrderSubmitVo orderSubmitVo, List<OrderItemEntity> orderItemEntities) {
    BigDecimal originalPrice = orderSubmitVo.getTotalPrice();
    BigDecimal nowPrice = orderItemEntities.stream().map(a -> {
      BigDecimal skuPrice = a.getSkuPrice();
      Integer skuQuantity = a.getSkuQuantity();
      return skuPrice.multiply(BigDecimal.valueOf(skuQuantity));
    }).reduce(BigDecimal.ZERO, BigDecimal::add);
    return nowPrice.compareTo(originalPrice);
  }

  private List<OrderItemEntity> getOrderItem(List<OrderItem> orderItems, OrderEntity orderEntity) {
    List<OrderItemEntity> list = orderItems.stream().map(a -> {
      OrderItemEntity orderItem = new OrderItemEntity();
      orderItem.setOrderSn(orderEntity.getOrderSn());
      orderItem.setSkuId(a.getSkuId());
      orderItem.setSkuPic(a.getImage());
      orderItem.setSkuName(a.getTitle());
      orderItem.setSkuPrice(a.getPrice());
      orderItem.setSkuQuantity(a.getCount());
      orderItem.setSpuId(a.getSpuId());
      orderItem.setGiftGrowth(a.getPrice().intValue());
      orderItem.setGiftIntegration(a.getPrice().intValue());
      //最后的金额
      orderItem.setRealAmount(a.getPrice().multiply(BigDecimal.valueOf(a.getCount())));
      return orderItem;
    }).toList();

    R spuByIds = spuFeign.getSpuByIds(list.stream().map(OrderItemEntity::getSpuId).toList());
    List<SpuInfoVo> data = spuByIds.getData(new TypeReference<>() {
    });
    Map<Long, SpuInfoVo> collect = data.stream()
        .collect(Collectors.toMap(SpuInfoVo::getId, item -> item));
    list.forEach(a -> {
      Long spuId = a.getSpuId();
      SpuInfoVo spuInfoVo = collect.get(spuId);
      a.setSpuName(spuInfoVo.getSpuName());
      a.setSpuBrand(spuInfoVo.getBrandId().toString());
      a.setCategoryId(spuInfoVo.getCatalogId());
    });

    return list;
  }

}




