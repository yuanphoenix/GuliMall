package com.atguigu.gulimall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {


  @Bean(destroyMethod = "shutdown")
  public RedissonClient redisson() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://10.200.200.1:6379");
    return Redisson.create(config);
  }
}
