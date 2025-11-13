package com.atguigu.gulimall.order;

import config.JacksonTimeConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.atguigu.gulimall.order.mapper")
@ComponentScan(basePackageClasses = {GulimallOrderApplication.class, JacksonTimeConfig.class})
@EnableDiscoveryClient
public class GulimallOrderApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallOrderApplication.class, args);
  }

}
