package com.atguigu.gulimall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.atguigu.gulimall.ware.mapper")
@EnableDiscoveryClient
public class GulimallWareApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallWareApplication.class, args);
  }

}
