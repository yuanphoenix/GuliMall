package com.atguigu.gulimall.search;

import config.JacksonTimeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackageClasses = {GulimallSearchApplication.class, JacksonTimeConfig.class})
public class GulimallSearchApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallSearchApplication.class, args);
  }

}
