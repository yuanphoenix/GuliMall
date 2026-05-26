package com.tifa.gulimallseckill;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class TestAAA {


  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private ObjectMapper objectMapper;



  @Test
  void testRedis() {
    var c = redisTemplate.boundZSetOps("seckill:sessions:start")
        .rangeByScore(0, ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toEpochSecond());
    System.out.println(c);

  }


}
