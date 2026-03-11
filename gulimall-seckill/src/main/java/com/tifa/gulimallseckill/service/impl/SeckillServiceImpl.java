package com.tifa.gulimallseckill.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tifa.gulimallseckill.entity.SeckillSessionEntityTo;
import com.tifa.gulimallseckill.feign.CouponFeign;
import com.tifa.gulimallseckill.service.SeckillService;
import constant.RedisConstant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import to.seckill.SeckillSkuRelationEntityTo;


/**
 * @author tifa
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

  private final CouponFeign couponFeign;
  private final RedisTemplate redisTemplate;
  private final RabbitTemplate rabbitTemplate;

  private final ObjectMapper objectMapper;


  public SeckillServiceImpl(CouponFeign couponFeign, RedisTemplate redisTemplate,
      RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
    this.couponFeign = couponFeign;
    this.redisTemplate = redisTemplate;
    this.rabbitTemplate = rabbitTemplate;
    this.objectMapper = objectMapper;
  }

  @Override
  public void uploadSecKillLatest3daySku() {
//扫描需要参加秒杀的活动
    List<SeckillSessionEntityTo> secKill3daysLatest = couponFeign.getSecKill3daysLatest();
    List<Long> sessionIds = secKill3daysLatest.stream().map(SeckillSessionEntityTo::getId).toList();

    for (var item : secKill3daysLatest) {
//      将活动按照起始时间放到redis中，使用的是zset。zset具有排序功能
      redisTemplate.boundZSetOps("seckill:sessions:zset")
          .add("seckill:session:" + item.getId(), item.getStartTime().atZone(
              ZoneId.of("Asia/Shanghai")).toEpochSecond());
    }

//    将这些信息保存到redis中
    //sec:kill:开始时间
    List<SeckillSkuRelationEntityTo> list = new ArrayList<>();
    for (SeckillSessionEntityTo a : secKill3daysLatest) {
      List<SeckillSkuRelationEntityTo> seckillSkuRelationEntities = a.getSeckillSkuRelationEntities();

      a.setSeckillSkuRelationEntities(null);
//      将活动场次信息放到单独的key中
      redisTemplate.opsForValue().set("seckill:session:" + a.getId(), a);

      //只能写成这个，不能从coupon微服务里修改
      seckillSkuRelationEntities.forEach(b -> {
        b.setStartTime(a.getStartTime());
        b.setEndTime(a.getEndTime());
        redisTemplate.opsForSet()
            .add("seckill:session:" + a.getId() + ":skus", b);
      });


    }
    if (list.isEmpty()) {
      log.info("没有可以上架的");
      return;
    }
    log.info("发送了sku上架信号");
    rabbitTemplate.convertAndSend("seckill-exchange", "seckill.sku", list);
  }

  @Override
  public List<SeckillSkuRelationEntityTo> getAllSecKillSku() {
    Set<String> keys2 = redisTemplate.keys(RedisConstant.SECOND_KILL_SKU_PREFIX + "*");
    List<SeckillSkuRelationEntityTo> tt = new ArrayList<>();

    keys2.forEach(a -> {
      BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(a);
      Set<String> promotionSessionIds = boundHashOperations.keys();
      promotionSessionIds.forEach(sessionId -> {
        Object object = boundHashOperations.get(sessionId);
        SeckillSkuRelationEntityTo seckillSkuRelationEntityTo = objectMapper.convertValue(object,
            SeckillSkuRelationEntityTo.class);
        if (LocalDateTime.now().isAfter(seckillSkuRelationEntityTo.getStartTime())
            && LocalDateTime.now().isBefore(seckillSkuRelationEntityTo.getEndTime())) {
          tt.add(seckillSkuRelationEntityTo);
        }

      });
    });
    return tt;
  }
}
