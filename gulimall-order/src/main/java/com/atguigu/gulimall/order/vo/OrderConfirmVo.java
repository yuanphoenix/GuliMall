package com.atguigu.gulimall.order.vo;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import to.cart.CartItem;

@Data
public class OrderConfirmVo {

  //ums_member_receive_address 收货人表
  List<MemberAddressVo> address;

  //所有选中的购物项目
  List<OrderItem> items;

  //发票记录
  //优惠券
  Integer integration;
  //  //订单总额
//  BigDecimal total;
  //应付总额
  BigDecimal payPrice;

  String token;
  String orderKey;


  public BigDecimal getTotal() {
    return items.stream().map(CartItem::getTotalPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
