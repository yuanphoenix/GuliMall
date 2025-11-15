package com.atguigu.gulimall.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import session.CustomRedisSession;

@SpringBootTest
class GulimallAuthServerApplicationTests {
  private RedisConnectionFactory redisConnectionFactory;
  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  @Autowired
  private GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer;
  @Autowired
  private ObjectProvider<RedisConnectionFactory> redisConnectionFactoryProvider;

  public RedisConnectionFactory getRedisConnectionFactory() {
    if (this.redisConnectionFactory == null) {
      this.redisConnectionFactory = this.redisConnectionFactoryProvider.getIfAvailable();
    }
    return this.redisConnectionFactory;
  }

  @Test
  void contextLoads() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    // 自定义序列化和反序列化器
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

    mapper.registerModule(javaTimeModule);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setHashKeySerializer(RedisSerializer.string());
      redisTemplate.setDefaultSerializer(genericJackson2JsonRedisSerializer);
    redisTemplate.setConnectionFactory(getRedisConnectionFactory());
    redisTemplate.afterPropertiesSet();
    System.out.println(redisTemplate.opsForValue()
        .get("\"custom:session:5f836439-0abb-47fd-b652-2c88e0384558\""));
//    CustomRedisSession customRedisSession = mapper.readValue(redisTemplate.opsForValue()
//            .get("\"custom:session:5f836439-0abb-47fd-b652-2c88e0384558\"").toString(),
//        CustomRedisSession.class);
//    System.out.println(customRedisSession);

    Object value = redisTemplate.opsForValue().get("\"custom:session:5f836439-0abb-47fd-b652-2c88e0384558\"");
    CustomRedisSession session = genericJackson2JsonRedisSerializer.deserialize(
        genericJackson2JsonRedisSerializer.serialize(value), CustomRedisSession.class);

    System.out.println(session);
  }
}
