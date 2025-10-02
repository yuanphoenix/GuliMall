package com.atguigu.gulimall.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallSearchApplicationTests {

  @Autowired
  private ElasticsearchClient esClient;


  class Product {

    private String id;
    private String name;
    private Double price;

    public Product(String id, String name, Double price) {
      this.id = id;
      this.name = name;
      this.price = price;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Double getPrice() {
      return price;
    }

    public void setPrice(Double price) {
      this.price = price;
    }
  }

  @Test
  public void testElasticsearchClientIndex() throws IOException {
    esClient.indices().create(c -> c
        .index("products")
    );
  }

  @Test
  public void testElasticsearchClientSearch() throws IOException {
    Product product = new Product("bk-1", "City bike", 123.0);
    IndexResponse response = esClient.index(i -> i
        .index("products")
        .id(product.id)
        .document(product));

    System.out.println(response);
  }
}
