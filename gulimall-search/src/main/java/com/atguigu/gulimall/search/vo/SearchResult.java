package com.atguigu.gulimall.search.vo;

import java.util.List;
import to.es.SkuEsModel;

public class SearchResult {

  private List<SkuEsModel> esModels;
  private Integer totalNum;
  private Integer currentPage;
  private Integer totalPage;

  private List<BrandVo> brandVoList;
  private List<AttrVo> attrVoList;
  private List<CategoryVo> categoryVoList;


  public List<SkuEsModel> getEsModels() {
    return esModels;
  }

  public void setEsModels(List<SkuEsModel> esModels) {
    this.esModels = esModels;
  }

  public Integer getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
    this.totalNum = totalNum;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public List<BrandVo> getBrandVoList() {
    return brandVoList;
  }

  public void setBrandVoList(
      List<BrandVo> brandVoList) {
    this.brandVoList = brandVoList;
  }

  public List<AttrVo> getAttrVoList() {
    return attrVoList;
  }

  public void setAttrVoList(List<AttrVo> attrVoList) {
    this.attrVoList = attrVoList;
  }

  public List<CategoryVo> getCategoryVoList() {
    return categoryVoList;
  }

  public void setCategoryVoList(
      List<CategoryVo> categoryVoList) {
    this.categoryVoList = categoryVoList;
  }

  public static class BrandVo {

    private Long brandId;
    private String brandName;
    private String brandImg;


    @Override
    public String toString() {
      return "BrandVo{" +
          "brandId=" + brandId +
          ", brandName='" + brandName + '\'' +
          ", brandImg='" + brandImg + '\'' +
          '}';
    }

    public Long getBrandId() {
      return brandId;
    }

    public void setBrandId(Long brandId) {
      this.brandId = brandId;
    }

    public String getBrandName() {
      return brandName;
    }

    public void setBrandName(String brandName) {
      this.brandName = brandName;
    }

    public String getBrandImg() {
      return brandImg;
    }

    public void setBrandImg(String brandImg) {
      this.brandImg = brandImg;
    }
  }


  public static class CategoryVo {

    private Long catId;
    private String categoryName;


    @Override
    public String toString() {
      return "CategoryVo{" +
          "catId=" + catId +
          ", categoryName='" + categoryName + '\'' +
          '}';
    }

    public Long getCatId() {
      return catId;
    }

    public void setCatId(Long catId) {
      this.catId = catId;
    }

    public String getCategoryName() {
      return categoryName;
    }

    public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
    }
  }

  public static class AttrVo {

    private Long attrId;
    private String attrName;
    private List<String> attrValue;


    @Override
    public String toString() {
      return "AttrVo{" +
          "attrId=" + attrId +
          ", attrName='" + attrName + '\'' +
          ", attrValue=" + attrValue +
          '}';
    }

    public Long getAttrId() {
      return attrId;
    }

    public void setAttrId(Long attrId) {
      this.attrId = attrId;
    }

    public String getAttrName() {
      return attrName;
    }

    public void setAttrName(String attrName) {
      this.attrName = attrName;
    }

    public List<String> getAttrValue() {
      return attrValue;
    }

    public void setAttrValue(List<String> attrValue) {
      this.attrValue = attrValue;
    }
  }


  @Override
  public String toString() {
    return "SearchResult{" +
        "esModels=" + esModels +
        ", totalNum=" + totalNum +
        ", currentPage=" + currentPage +
        ", totalPage=" + totalPage +
        ", brandVoList=" + brandVoList +
        ", attrVoList=" + attrVoList +
        ", categoryVoList=" + categoryVoList +
        '}';
  }
}
