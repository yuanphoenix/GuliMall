package com.atguigu.gulimall.product;

import config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.atguigu.gulimall.product.mapper")
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {GulimallProductApplication.class, MybatisPlusConfig.class})
public class GulimallProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallProductApplication.class, args);
  }

}
