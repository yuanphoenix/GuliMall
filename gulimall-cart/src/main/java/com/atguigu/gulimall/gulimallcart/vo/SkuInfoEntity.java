package com.atguigu.gulimall.gulimallcart.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.Data;

/**
 * sku信息
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SkuInfoEntity {

  /**
   * skuId
   */
  private Long skuId;


  /**
   * spuId
   */
  private Long spuId;

  /**
   * sku名称
   */
  private String skuName;

  private Long nums;

  /**
   * sku介绍描述
   */
  private String skuDesc;

  /**
   * 所属分类id
   */
  private Long catalogId;

  /**
   * 品牌id
   */
  private Long brandId;

  /**
   * 默认图片
   */
  private String skuDefaultImg;

  /**
   * 标题
   */
  private String skuTitle;

  /**
   * 副标题
   */
  private String skuSubtitle;

  /**
   * 价格
   */
  private BigDecimal price;

  /**
   * 销量
   */
  private Long saleCount;

}