package com.atguigu.gulimall.auth;

import config.JacksonTimeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {JacksonTimeConfig.class, GulimallAuthServerApplication.class})

@SpringBootApplication
public class GulimallAuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallAuthServerApplication.class, args);
  }

}
