package com.tifa.gulimallseckill;

import config.JacksonTimeConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {JacksonTimeConfig.class, GulimallSeckillApplication.class})
@SpringBootApplication
public class GulimallSeckillApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallSeckillApplication.class, args);
  }

}
