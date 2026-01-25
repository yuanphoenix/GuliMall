package com.atguigu.gulimall.order.vo;

import to.cart.CartItemTo;

public class OrderItemTo extends CartItemTo {



  //是否有库存
  private Boolean hasStock;


  public void setHasStock(Boolean hasStock) {
    this.hasStock = hasStock;
  }

  public Boolean getHasStock() {
    return hasStock != null && hasStock;
  }

}
