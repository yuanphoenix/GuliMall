package com.atguigu.gulimall.product.listener;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.service.SkuInfoService;
import com.rabbitmq.client.Channel;
import constant.RedisConstant;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import to.SkuInfoEntityTo;
import to.seckill.SeckillSkuRelationEntityTo;

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
  public void secondKillSkuPublish(List<SeckillSkuRelationEntityTo> skuRelationEntityToList,
      Message message,
      Channel channel) {
    List<Long> skuIds = skuRelationEntityToList.stream().map(SeckillSkuRelationEntityTo::getSkuId)
        .distinct()
        .toList();

    Map<Long, SkuInfoEntity> collect = skuInfoService.lambdaQuery()
        .in(SkuInfoEntity::getSkuId, skuIds).list()
        .stream().collect(Collectors.toMap(SkuInfoEntity::getSkuId, Function.identity()));

    skuRelationEntityToList.forEach(a -> {

      BoundHashOperations<String, String, Object> stringObjectObjectBoundHashOperations = redisTemplate.boundHashOps(
          RedisConstant.SECOND_KILL_SKU_PREFIX + a.getSkuId());
      Long skuId = a.getSkuId();
      SkuInfoEntity orDefault = collect.getOrDefault(skuId, new SkuInfoEntity());
      SkuInfoEntityTo skuInfoEntityTo = new SkuInfoEntityTo();
      BeanUtils.copyProperties(orDefault, skuInfoEntityTo);
      a.setSkuInfoEntityTo(skuInfoEntityTo);
      stringObjectObjectBoundHashOperations.putIfAbsent(a.getPromotionSessionId().toString(), a);
    });

    try {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
