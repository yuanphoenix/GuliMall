package com.atguigu.gulimall.ware.listener;

import com.atguigu.gulimall.ware.service.WareSkuService;
import com.rabbitmq.client.Channel;
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


  @RabbitListener(queues = "stock.release.queue")
  public void handle(WareTo wareTo, Message message,Channel channel) {
    wareSkuService.unlockStock(wareTo);
  }

}
