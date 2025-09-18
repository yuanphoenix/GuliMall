package com.atguigu.gulimall.search.config;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuliMallElasticSearchConfig {

  @Bean
  public ElasticsearchClient esClient() {
    return ElasticsearchClient.of(b -> b
        .host("http://10.200.200.1:9200")
    );
  }

}
