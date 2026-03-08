package com.tifa.gulimallseckill;

import com.fasterxml.jackson.databind.ObjectMapper;
import constant.RedisConstant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import to.seckill.SeckillSkuRelationEntityTo;

@SpringBootTest
public class TestAAA {


  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private ObjectMapper objectMapper;


  @Test
  void ttt(){
    LocalDate localDate = LocalDate.now();
    System.out.println(localDate);

  }


  @Test
  void testRedis() {
    Set<String> keys2 = redisTemplate.keys(RedisConstant.SECOND_KILL_SKU_PREFIX + "*");
    System.out.println(keys2);
    List<SeckillSkuRelationEntityTo> tt = new ArrayList<>();
    keys2.forEach(a -> {
      BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(a);
      Set<String> promotionSessionIds = boundHashOperations.keys();
      promotionSessionIds.forEach(sessionId -> {
        Object object = boundHashOperations.get(sessionId);
        SeckillSkuRelationEntityTo seckillSkuRelationEntityTo = objectMapper.convertValue(object,
            SeckillSkuRelationEntityTo.class);
        tt.add(seckillSkuRelationEntityTo);
      });
    });
    System.out.println(tt);
  }


}
