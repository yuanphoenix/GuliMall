/**
 * Copyright 2025 bejson.com
 */
package com.atguigu.gulimall.product.vo.spuinfo;

import java.math.BigDecimal;

/**
 * Auto-generated: 2025-06-01 21:1:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MemberPrice {

  private Long id;
  private String name;
  private BigDecimal price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}