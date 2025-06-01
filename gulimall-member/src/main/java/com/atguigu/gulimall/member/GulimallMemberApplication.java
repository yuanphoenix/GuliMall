package com.atguigu.gulimall.member;

import config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.atguigu.gulimall.member.mapper")
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {GulimallMemberApplication.class, MybatisPlusConfig.class})

@EnableFeignClients(basePackages = "com.atguigu.gulimall.member.feign")
public class GulimallMemberApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallMemberApplication.class, args);
  }

}
