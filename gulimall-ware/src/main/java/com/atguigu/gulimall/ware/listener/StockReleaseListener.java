package com.atguigu.gulimall.ware.listener;

import com.atguigu.gulimall.ware.service.WareSkuService;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import to.ware.WareTo;

@Component
public class StockReleaseListener {

  private static final Logger log = LoggerFactory.getLogger(StockReleaseListener.class);
  private final WareSkuService wareSkuService;

  public StockReleaseListener(WareSkuService wareSkuService) {
    this.wareSkuService = wareSkuService;
  }


  @RabbitListener(queues = "stock-release-queue")
  public void handle(WareTo wareTo, Message message, Channel channel) throws IOException {
    log.info("库存开始解锁，{}", wareTo);
    wareSkuService.unlockStock(wareTo);
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
  }

}
