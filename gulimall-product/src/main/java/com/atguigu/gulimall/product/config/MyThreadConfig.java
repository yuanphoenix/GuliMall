package com.atguigu.gulimall.product.config;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 我的线程池
 * @author tifa
 */
@Configuration
public class MyThreadConfig {


  @Bean
  public ThreadPoolExecutor threadPoolExecutor(
      ThreadPoolConfigProperties threadPoolConfigProperties) {
    return new ThreadPoolExecutor(threadPoolConfigProperties.getCorePoolSize(),
        threadPoolConfigProperties.getMaxPoolSize(),
        threadPoolConfigProperties.getKeepAliveTime(), TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(100000),
        Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
  }
}
