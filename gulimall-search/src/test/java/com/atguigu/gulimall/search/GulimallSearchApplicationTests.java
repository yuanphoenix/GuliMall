package com.atguigu.gulimall.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootTest
class GulimallSearchApplicationTests {

  @Autowired
  private ElasticsearchClient elasticsearchClient;

  @Test
  void contextLoads() {
    System.out.println(elasticsearchClient);
  }

}
