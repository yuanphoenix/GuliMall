package com.atguigu.gulimall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import com.atguigu.gulimall.search.constant.EsConstant;
import com.atguigu.gulimall.search.service.ProductSaveService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import to.es.SkuEsModel;

@Service
public class ProductSaveServiceImpl implements ProductSaveService {

  private static final Logger logger = LoggerFactory.getLogger(ProductSaveServiceImpl.class);

  private final ElasticsearchClient esClient;

  public ProductSaveServiceImpl(ElasticsearchClient esClient) {
    this.esClient = esClient;
  }

  @Override
  public void productStatusUp(List<SkuEsModel> skuEsModels) {
    //保存数据到ES中
    BulkRequest.Builder br = new BulkRequest.Builder();
    for (SkuEsModel skuEsModel : skuEsModels) {
      br.operations(op -> op
          .index(idx -> idx
              .index(EsConstant.PRODUCTINDEX)
              .id(skuEsModel.getSkuId().toString())
              .document(skuEsModel)
          )
      );
    }

    try {
      BulkResponse result = esClient.bulk(br.build());

      if (result.errors()) {
        // 如果存在错误，记录错误的文档
        result.items().forEach(item -> {
          if (item.error() != null) {
            logger.error("索引文档失败，SKU ID: {}, 错误信息: {}", item.id(), item.error().reason());
          }
        });
      } else {
        logger.info("批量产品状态更新成功");
      }

    } catch (ElasticsearchException e) {
      // 捕获 Elasticsearch 相关的异常
      logger.error("Elasticsearch 请求失败，异常信息: {}", e.error(), e);
    } catch (Exception e) {
      // 捕获其他异常
      logger.error("批量产品状态更新时发生未知错误", e);
    }
  }
}
