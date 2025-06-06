/**
 * Copyright 2025 bejson.com
 */
package com.atguigu.gulimall.product.vo.spuinfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2025-06-01 21:1:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Skus {

  private List<Attr> attr;
  private String skuName;
  private BigDecimal price;
  private String skuTitle;
  private String skuSubtitle;
  private List<Images> images;
  private List<String> descar;
  private int fullCount;
  private BigDecimal discount;
  private int countStatus;
  private BigDecimal fullPrice;
  private BigDecimal reducePrice;
  private int priceStatus;
  private List<MemberPrice> memberPrice;


  public List<Attr> getAttr() {
    return attr;
  }

  public void setAttr(List<Attr> attr) {
    this.attr = attr;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getSkuTitle() {
    return skuTitle;
  }

  public void setSkuTitle(String skuTitle) {
    this.skuTitle = skuTitle;
  }

  public String getSkuSubtitle() {
    return skuSubtitle;
  }

  public void setSkuSubtitle(String skuSubtitle) {
    this.skuSubtitle = skuSubtitle;
  }

  public List<Images> getImages() {
    return images;
  }

  public void setImages(List<Images> images) {
    this.images = images;
  }

  public List<String> getDescar() {
    return descar;
  }

  public void setDescar(List<String> descar) {
    this.descar = descar;
  }

  public int getFullCount() {
    return fullCount;
  }

  public void setFullCount(int fullCount) {
    this.fullCount = fullCount;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public int getCountStatus() {
    return countStatus;
  }

  public void setCountStatus(int countStatus) {
    this.countStatus = countStatus;
  }

  public BigDecimal getFullPrice() {
    return fullPrice;
  }

  public void setFullPrice(BigDecimal fullPrice) {
    this.fullPrice = fullPrice;
  }

  public BigDecimal getReducePrice() {
    return reducePrice;
  }

  public void setReducePrice(BigDecimal reducePrice) {
    this.reducePrice = reducePrice;
  }

  public int getPriceStatus() {
    return priceStatus;
  }

  public void setPriceStatus(int priceStatus) {
    this.priceStatus = priceStatus;
  }

  public List<MemberPrice> getMemberPrice() {
    return memberPrice;
  }

  public void setMemberPrice(
      List<MemberPrice> memberPrice) {
    this.memberPrice = memberPrice;
  }
}