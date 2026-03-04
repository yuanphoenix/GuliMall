package com.atguigu.gulimall.ware.listener;

import com.atguigu.gulimall.ware.service.WareSkuService;
import com.rabbitmq.client.Channel;
import constant.RabbitMqMessageEnum;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import to.order.OrderPayedEvent;
import to.ware.WareTo;

@Component
public class StockReleaseListener {
  private final WareSkuService wareSkuService;
  public StockReleaseListener(WareSkuService wareSkuService) {
    this.wareSkuService = wareSkuService;
  }


  @RabbitListener(queues = "stock-release-queue")
  public void handle(WareTo wareTo, Message message, Channel channel) throws IOException {
    var result = wareSkuService.unlockStock(wareTo);
    if (result == RabbitMqMessageEnum.SUCCESS) {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } else {
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
  }

  @RabbitListener(queues = "stock-minus-queue")
  public void stockMinus(OrderPayedEvent orderPayedEvent, Message message, Channel channel) {
    String orderSn = orderPayedEvent.getOrderSn();
    var result = wareSkuService.minusStock(orderSn);
    try {
      if (result == RabbitMqMessageEnum.FAILURE) {
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
      } else {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
