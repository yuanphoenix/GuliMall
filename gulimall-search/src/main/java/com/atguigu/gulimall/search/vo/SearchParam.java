package com.atguigu.gulimall.search.vo;

import java.util.List;

public class SearchParam {

  private String keyWord;//检索关键字
  private String catalog3Id;//3级分类的Id
  private String sort;//排序的条件，这个条件会有很多种

  private Integer hasStock;//显示是否有货
  private String skuPrice;//显示价格区间
  private List<Long> brandIds;//筛选各个品牌
  private List<String> attrs;//按照属性值筛选
  private Integer pageNum;//分页的页码


  public String getKeyWord() {
    return keyWord;
  }

  public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
  }

  public String getCatalog3Id() {
    return catalog3Id;
  }

  public void setCatalog3Id(String catalog3Id) {
    this.catalog3Id = catalog3Id;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public Integer getHasStock() {
    return hasStock;
  }

  public void setHasStock(Integer hasStock) {
    this.hasStock = hasStock;
  }

  public String getSkuPrice() {
    return skuPrice;
  }

  public void setSkuPrice(String skuPrice) {
    this.skuPrice = skuPrice;
  }

  public List<Long> getBrandIds() {
    return brandIds;
  }

  public void setBrandIds(List<Long> brandIds) {
    this.brandIds = brandIds;
  }

  public List<String> getAttrs() {
    return attrs;
  }

  public void setAttrs(List<String> attrs) {
    this.attrs = attrs;
  }

  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }
}
