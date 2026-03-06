package com.atguigu.gulimall.coupon.vo;

import utils.PageDTO;

public class SessionRealtionDTO extends PageDTO {
  private Long promotionSessionId;

  public Long getPromotionSessionId() {
    return promotionSessionId;
  }

  public void setPromotionSessionId(Long promotionSessionId) {
    this.promotionSessionId = promotionSessionId;
  }
}
