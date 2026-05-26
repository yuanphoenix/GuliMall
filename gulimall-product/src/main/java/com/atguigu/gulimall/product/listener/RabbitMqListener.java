package com.atguigu.gulimall.product.listener;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.service.SkuInfoService;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {


  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private SkuInfoService skuInfoService;

  /**
   * 秒杀上架，
   */
  @RabbitListener(
      bindings = @QueueBinding(
          value = @Queue(name = "seckill.sku.queue", durable = "true", exclusive = "false"),
          exchange = @Exchange(name = "seckill-exchange", type = ExchangeTypes.TOPIC),
          key = "seckill.sku"
      )
  )
  public void secondKillSkuPublish(List<String> sessionAndSkuIdList,
      Message message,
      Channel channel) {
    try {

      Set<Long> skuIds = sessionAndSkuIdList.stream()
          .map(a -> Long.parseLong(a.substring(a.indexOf("_") + 1))).collect(Collectors.toSet());

      List<SkuInfoEntity> list = skuInfoService.lambdaQuery()
          .in(SkuInfoEntity::getSkuId, skuIds).list();

      Map<Long, SkuInfoEntity> skuMap = list.stream()
          .collect(Collectors.toMap(SkuInfoEntity::getSkuId, v -> v));

      for (String key : sessionAndSkuIdList) {

        long skuId = Long.parseLong(key.substring(key.indexOf("_") + 1));
        SkuInfoEntity sku = skuMap.get(skuId);

//        防止热key
        redisTemplate.opsForValue().set("seckill:sku:" + key, sku);
      }
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (IOException e) {
      try {
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }

      throw new RuntimeException(e);
    }

  }
}
