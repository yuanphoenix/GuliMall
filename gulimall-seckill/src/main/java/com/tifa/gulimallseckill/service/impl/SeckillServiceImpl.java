package com.tifa.gulimallseckill.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tifa.gulimallseckill.entity.SeckillSessionEntityTo;
import com.tifa.gulimallseckill.feign.CouponFeign;
import com.tifa.gulimallseckill.service.SeckillService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import to.SkuInfoEntityTo;
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
    List<String> list = new ArrayList<>();

    for (SeckillSessionEntityTo lightningDeal : secKill3daysLatest) {
//      将活动按照起始时间放到redis中，使用的是zset。zset具有排序功能
      redisTemplate.boundZSetOps("seckill:sessions:start")
          .add("seckill:session:" + lightningDeal.getId(),
              lightningDeal.getStartTime().atZone(ZoneId.of("Asia/Shanghai")).toEpochSecond());
      redisTemplate.boundZSetOps("seckill:sessions:end")
          .add("seckill:session:" + lightningDeal.getId(),
              lightningDeal.getEndTime().atZone(ZoneId.of("Asia/Shanghai")).toEpochSecond());

//    将这些信息保存到redis中
//    sec:kill:开始时间
      List<SeckillSkuRelationEntityTo> seckillSkuRelationEntities = lightningDeal.getSeckillSkuRelationEntities();
//      将活动实体里的关联信息置为空，因为向redis中存储用不到。其实前面已经保存过了。
      lightningDeal.setSeckillSkuRelationEntities(null);
//      将活动场次信息放到单独的key中
      redisTemplate.opsForValue().set("seckill:session:" + lightningDeal.getId(), lightningDeal);

      seckillSkuRelationEntities.forEach(b -> {

        String sessionAndSkuId = lightningDeal.getId() + "_" + b.getSkuId();

//      建立活动id和skuid的关系
        redisTemplate.opsForSet()
            .add("seckill:session:" + lightningDeal.getId() + ":skus", sessionAndSkuId);

//        将库存存入redis中
        redisTemplate.opsForValue().set("seckill:stock:" + sessionAndSkuId, b.getSeckillCount());

        redisTemplate.boundHashOps("seckill:sku:relation").put(sessionAndSkuId, b);
        list.add(sessionAndSkuId);
      });

    }
    log.info("发送了sku上架信号");
    rabbitTemplate.convertAndSend("seckill-exchange", "seckill.sku", list);
  }

  /**
   * 筛选出正在进行中的活动的redis key
   * <p>
   * 类似这种 seckill:session:1
   *
   * @return
   */
  private Set<String> filterSession() {
    long epochSecond = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toEpochSecond();
    Set<String> startTime = redisTemplate.boundZSetOps("seckill:sessions:start")
        .rangeByScore(0, epochSecond);

    Set<String> endTime = redisTemplate.boundZSetOps("seckill:sessions:end")
        .rangeByScore(0, epochSecond);
    startTime.removeAll (endTime);
    return startTime;
  }

  @Override
  public List<SeckillSkuRelationEntityTo> getAllSecKillSku() {
    List<SeckillSkuRelationEntityTo> result = new ArrayList<>();

    Set<String> sessionKeys = filterSession();
    sessionKeys.forEach(a -> {

//"1_1"
      List<String> members = redisTemplate.boundSetOps(a + ":skus").members().stream().toList();
      for (var item : members) {
        Object object = redisTemplate.boundHashOps("seckill:sku:relation").get(item);
        SeckillSkuRelationEntityTo seckillSkuRelationEntityTo = objectMapper.convertValue(object,
            SeckillSkuRelationEntityTo.class);
        Object object1 = redisTemplate.opsForValue().get("seckill:sku:" + item);
        SkuInfoEntityTo skuInfoEntityTo = objectMapper.convertValue(object1, SkuInfoEntityTo.class);
        seckillSkuRelationEntityTo.setSkuInfoEntityTo(skuInfoEntityTo);
        result.add(seckillSkuRelationEntityTo);
      }
    });
    return result;
  }
}
