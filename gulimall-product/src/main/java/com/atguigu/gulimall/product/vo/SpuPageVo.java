package com.atguigu.gulimall.product.vo;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import utils.PageDTO;

public class SpuPageVo extends PageDTO {
  private Long catalogId;
  private Long brandId;
  private Integer status;


  public Long getCatalogId() {
    return catalogId;
  }

  public void setCatalogId(Long catalogId) {
    this.catalogId = catalogId;
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
