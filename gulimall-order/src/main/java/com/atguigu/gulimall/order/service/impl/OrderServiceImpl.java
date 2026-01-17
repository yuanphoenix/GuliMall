package com.atguigu.gulimall.order.service.impl;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.feign.CartFeign;
import com.atguigu.gulimall.order.feign.MemberFeign;
import com.atguigu.gulimall.order.feign.SkuFeign;
import com.atguigu.gulimall.order.mapper.OrderMapper;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.MemberAddressVo;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.Channel;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import to.MemberEntityVo;
import to.cart.CartItem;

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
  private SkuFeign skuFeign;

  @Autowired
  private ThreadPoolExecutor threadPoolExecutor;


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
    CompletableFuture<List<MemberAddressVo>> future1 = CompletableFuture.supplyAsync(
        () -> memberFeign.getMemberByMemberId(
            memberEntityVo.getId()), threadPoolExecutor);

    CompletableFuture<List<CartItem>> future2 = CompletableFuture.supplyAsync(
            () -> cartFeign.getCartItems(memberEntityVo.getId()), threadPoolExecutor)

        .thenApply((cartItems) -> {
          List<CartItem> checkedCartItems = cartItems.stream()
              .filter(CartItem::getChecked)
              .toList();

          Map<Long, BigDecimal> cartItemMap = skuFeign.skuInfoEntityList(checkedCartItems).stream()
              .collect(Collectors.toMap(CartItem::getSkuId, CartItem::getPrice));

          checkedCartItems.forEach(cartItem ->
              cartItem.setPrice(cartItemMap.get(cartItem.getSkuId())));
          return checkedCartItems;
        });

    CompletableFuture.allOf(future1, future2).join();
    confirmVo.setAddress(future1.join());
    Integer integration = memberEntityVo.getIntegration();
    confirmVo.setIntegration(integration);
    confirmVo.setItems(future2.join());
    return confirmVo;
  }
}




