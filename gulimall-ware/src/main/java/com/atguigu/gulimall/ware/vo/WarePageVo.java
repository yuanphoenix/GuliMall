package com.atguigu.gulimall.ware.vo;

import utils.PageDTO;

public class WarePageVo extends PageDTO {

  private Long skuId;
  private Long wareId;
  private Integer status;

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Long getWareId() {
    return wareId;
  }

  public void setWareId(Long wareId) {
    this.wareId = wareId;
  }
}
