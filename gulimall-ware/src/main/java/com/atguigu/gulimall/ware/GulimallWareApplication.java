package com.atguigu.gulimall.ware;

import config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.atguigu.gulimall.ware.mapper")
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = { GulimallWareApplication.class,MybatisPlusConfig.class})
public class GulimallWareApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallWareApplication.class, args);
  }

}
