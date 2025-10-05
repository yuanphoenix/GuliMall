package com.atguigu.gulimall.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import to.es.SkuEsModel;

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
    String a = "445_";
    String[] split = a.split("_",-1);
    System.out.println(Arrays.toString(split));
    System.out.println(split[1]);
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


  @Test
  public void search() throws IOException {
    SearchResponse<SkuEsModel> search = esClient.search(s ->
            s.index("product").query(q ->
                q.match(t ->
                    t.field("skuTitle")
                        .query("手机")
                )

            )
        , SkuEsModel.class);
    System.out.println(search);
  }
}
