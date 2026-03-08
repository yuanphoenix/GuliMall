package com.tifa.gulimallseckill.service.impl;

import com.tifa.gulimallseckill.entity.SeckillSessionEntityTo;
import com.tifa.gulimallseckill.feign.CouponFeign;
import com.tifa.gulimallseckill.service.SeckillService;
import constant.RedisConstant;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import to.seckill.SeckillSkuRelationEntityTo;


@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

  private final CouponFeign couponFeign;
  private final RedisTemplate redisTemplate;
  private final RabbitTemplate rabbitTemplate;


  public SeckillServiceImpl(CouponFeign couponFeign, RedisTemplate redisTemplate,
      RabbitTemplate rabbitTemplate) {
    this.couponFeign = couponFeign;
    this.redisTemplate = redisTemplate;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void uploadSecKillLatest3daySku() {
//扫描需要参加秒杀的活动
    List<SeckillSessionEntityTo> secKill3daysLatest = couponFeign.getSecKill3daysLatest();
//    将这些信息保存到redis中
    //sec:kill:开始时间
    List<SeckillSkuRelationEntityTo> list = new ArrayList<>();
    secKill3daysLatest.forEach(a -> {
      BoundHashOperations<String, String, SeckillSessionEntityTo> stringObjectObjectBoundHashOperations = redisTemplate.boundHashOps(
          RedisConstant.SECOND_KILL_PREFIX + a.getStartTime().toString());
      String hashKey = a.getId().toString();
//      如果这个活动已经上了，那么就不再重复上了。幂等性
      if (Boolean.TRUE.equals(stringObjectObjectBoundHashOperations.putIfAbsent(hashKey, a))) {
        list.addAll(a.getSeckillSkuRelationEntities());
      }
    });
    if (list.isEmpty()) {
      log.info("没有可以上架的");
      return;
    }
    log.info("发送了sku上架信号");
    rabbitTemplate.convertAndSend("seckill-exchange", "seckill.sku", list);
  }
}
