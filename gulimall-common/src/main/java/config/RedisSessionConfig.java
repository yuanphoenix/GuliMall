package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * 这个是 redissession的配置类
 */
@Configuration
public class RedisSessionConfig {

  private final Logger logger = LoggerFactory.getLogger(RedisSessionConfig.class);


  @Bean
  public RedisSerializer<Object> springSessionDefaultRedisSerializer(ObjectMapper objectMapper) {
      return new GenericJackson2JsonRedisSerializer(
          objectMapper);
  }

  @Bean
  public RedisSerializer<String> redisKeySerializer() {
    return new StringRedisSerializer();
  }


  @Primary
  @Bean
  public CookieSerializer cookieSerializer() {
    DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
    defaultCookieSerializer.setDomainName("gulimall.com");
    defaultCookieSerializer.setCookieName("GULISESSION");
    return defaultCookieSerializer;
  }
}
