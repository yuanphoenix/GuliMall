package com.atguigu.gulimall.order.listener;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import to.ware.WareTo;

@Slf4j
@Component
public class OrderListener {

  private final OrderService orderService;
  private final RabbitTemplate rabbitTemplate;

  public OrderListener(OrderService orderService, RabbitTemplate rabbitTemplate) {
    this.orderService = orderService;
    this.rabbitTemplate = rabbitTemplate;
  }

  @RabbitListener(queues = "order.release.queue")
  public void releaseOrder(OrderEntity orderEntity, Message message, Channel channel)
      throws IOException {
    boolean update = orderService.update(
        new LambdaUpdateWrapper<OrderEntity>()
            .eq(OrderEntity::getStatus, 0)
            .eq(OrderEntity::getDeleteStatus, 0)
            .eq(OrderEntity::getOrderSn, orderEntity.getOrderSn())
            .set(OrderEntity::getDeleteStatus, 1));
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
    rabbitTemplate.convertAndSend("stock-event-exchange", "stock.release.stock", wareTo);
    try {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (IOException e) {
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
  }

}
