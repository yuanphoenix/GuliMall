package com.atguigu.gulimall.order.listener;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.feign.WareFeign;
import com.atguigu.gulimall.order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import to.ware.WareTo;

@Component
public class OrderListener {

  private final OrderService orderService;
  private final WareFeign wareFeign;
  private final RabbitTemplate rabbitTemplate;

  public OrderListener(OrderService orderService, WareFeign wareFeign,
      RabbitTemplate rabbitTemplate) {
    this.orderService = orderService;
    this.wareFeign = wareFeign;
    this.rabbitTemplate = rabbitTemplate;
  }

  @RabbitListener(queues = "order.release.queue")
  public void releaseOrder(OrderEntity orderEntity, Message message, Channel channel) {
    boolean update = orderService.update(
        new LambdaUpdateWrapper<OrderEntity>()
            .eq(OrderEntity::getStatus, 0)
            .eq(OrderEntity::getDeleteStatus, 0)
            .eq(OrderEntity::getOrderSn, orderEntity.getOrderSn())
            .set(OrderEntity::getDeleteStatus, 1));
    if (!update) {
      return;
    }

    WareTo wareTo = new WareTo();
    wareTo.setOrderSn(orderEntity.getOrderSn());

    rabbitTemplate.convertAndSend("stock-event-exchange", "stock.release.queue", wareTo);

  }

}
