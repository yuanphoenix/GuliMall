package com.tifa.gulimallseckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class GulimallSeckillApplication {

  public static void main(String[] args) {
    SpringApplication.run(GulimallSeckillApplication.class, args);
  }

}
