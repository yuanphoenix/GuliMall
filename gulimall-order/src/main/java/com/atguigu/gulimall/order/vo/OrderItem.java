package com.atguigu.gulimall.order.vo;

import to.cart.CartItem;

public class OrderItem extends CartItem {


  //是否有库存
  private Boolean hasStock;

  public Boolean getHasStock() {
    return hasStock != null && hasStock;
  }

  public void setHasStock(Boolean hasStock) {
    this.hasStock = hasStock;
  }
}
