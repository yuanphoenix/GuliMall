package com.atguigu.gulimall.order.config;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadConfig {

  @Bean
  public ThreadPoolExecutor threadPoolExecutor() {
    return new ThreadPoolExecutor(30, 50, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000),
        Executors.defaultThreadFactory(), new AbortPolicy());
  }

}
