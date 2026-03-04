package com.atguigu.gulimall.order.listener;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.rabbitmq.client.Channel;
import constant.RabbitMqMessageEnum;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import to.order.OrderPayedEvent;
import to.ware.WareTo;

/***
 * 最严谨的状态是只有达到了正常预期的行为才可以发ack。
 *
 * basicNack都应该记录到数据库中
 *
 */
@Slf4j
@Component
public class OrderListener {

  private final OrderService orderService;
  private final RabbitTemplate rabbitTemplate;

  public OrderListener(OrderService orderService, RabbitTemplate rabbitTemplate) {
    this.orderService = orderService;
    this.rabbitTemplate = rabbitTemplate;
  }


  @RabbitListener(queues = "order.payed.queue")
  public void payed(OrderPayedEvent orderPayedEvent, Message message, Channel channel) {
    String orderSn = orderPayedEvent.getOrderSn();
    var result = orderService.changeOrderStateToPayed(orderSn);
    try {
      switch (result) {
        case RETRY ->
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        case FAILURE ->
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        case IDEMPOTENT, SUCCESS ->
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 通过延时队列关闭订单
   *
   * @param orderEntity
   * @param message
   * @param channel
   * @throws IOException
   */
  @RabbitListener(queues = "order.release.queue")
  public void releaseOrder(OrderEntity orderEntity, Message message, Channel channel)
      throws IOException {

    /**
     * 订单时候先支付宝闭单
     */
    var closeResult = orderService.closeOrder(orderEntity);
    //支付宝闭单失败
    if (closeResult != RabbitMqMessageEnum.SUCCESS) {
      var result = orderService.changeOrderStateToPayed(orderEntity.getOrderSn());
      switch (result) {
        case SUCCESS -> channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        case RETRY -> channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,
            !message.getMessageProperties().getRedelivered());
        case FAILURE ->
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        case null, default -> {
          channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
      }
      return;
    }

    boolean update = orderService.update(
        new LambdaUpdateWrapper<OrderEntity>()
            .eq(OrderEntity::getStatus, 0)
            .eq(OrderEntity::getDeleteStatus, 0)
            .eq(OrderEntity::getOrderSn, orderEntity.getOrderSn())
            .set(OrderEntity::getStatus, 4));
//    幂等
    if (!update) {
      try {
        log.info("没有这个订单，或者之前状态已经修改过了");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
      } catch (IOException e) {
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
      }
      return;
    }

    WareTo wareTo = new WareTo();
    wareTo.setOrderSn(orderEntity.getOrderSn());
    //这个在极端情况下会发不出去
    //每一个消息都可以做好日志记录（给数据库保存每一个消息的详细信息）
    rabbitTemplate.convertAndSend("stock-event-exchange", "stock.release.stock", wareTo);
    try {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (IOException e) {
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
  }

}
