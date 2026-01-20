package com.atguigu.gulimall.order.vo;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderSubmitVo {

  //地址id
  private Long addrId;
  //付款方式
  private Integer payType;
  //从购物车再取一遍


  private String token;
  private String orderToken;
  //验价
  private BigDecimal totalPrice;
}
