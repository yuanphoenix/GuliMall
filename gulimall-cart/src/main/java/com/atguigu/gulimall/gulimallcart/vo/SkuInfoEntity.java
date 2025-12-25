package com.atguigu.gulimall.gulimallcart.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;

/**
 * sku信息
 *
 * @TableName pms_sku_info
 */
public class SkuInfoEntity {

  /**
   * skuId
   */
  @TableId(type = IdType.AUTO)
  private Long skuId;



  /**
   * spuId
   */
  private Long spuId;

  /**
   * sku名称
   */
  private String skuName;

  /**
   * sku介绍描述
   */
  private String skuDesc;

  /**
   * 所属分类id
   */
  private Long catalogId;

  /**
   * 品牌id
   */
  private Long brandId;

  /**
   * 默认图片
   */
  private String skuDefaultImg;

  /**
   * 标题
   */
  private String skuTitle;

  /**
   * 副标题
   */
  private String skuSubtitle;

  /**
   * 价格
   */
  private BigDecimal price;

  /**
   * 销量
   */
  private Long saleCount;

  /**
   * skuId
   */
  public Long getSkuId() {
    return skuId;
  }

  /**
   * skuId
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * spuId
   */
  public Long getSpuId() {
    return spuId;
  }

  /**
   * spuId
   */
  public void setSpuId(Long spuId) {
    this.spuId = spuId;
  }

  /**
   * sku名称
   */
  public String getSkuName() {
    return skuName;
  }

  /**
   * sku名称
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  /**
   * sku介绍描述
   */
  public String getSkuDesc() {
    return skuDesc;
  }

  /**
   * sku介绍描述
   */
  public void setSkuDesc(String skuDesc) {
    this.skuDesc = skuDesc;
  }

  /**
   * 所属分类id
   */
  public Long getCatalogId() {
    return catalogId;
  }

  /**
   * 所属分类id
   */
  public void setCatalogId(Long catalogId) {
    this.catalogId = catalogId;
  }

  /**
   * 品牌id
   */
  public Long getBrandId() {
    return brandId;
  }

  /**
   * 品牌id
   */
  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  /**
   * 默认图片
   */
  public String getSkuDefaultImg() {
    return skuDefaultImg;
  }

  /**
   * 默认图片
   */
  public void setSkuDefaultImg(String skuDefaultImg) {
    this.skuDefaultImg = skuDefaultImg;
  }

  /**
   * 标题
   */
  public String getSkuTitle() {
    return skuTitle;
  }

  /**
   * 标题
   */
  public void setSkuTitle(String skuTitle) {
    this.skuTitle = skuTitle;
  }

  /**
   * 副标题
   */
  public String getSkuSubtitle() {
    return skuSubtitle;
  }

  /**
   * 副标题
   */
  public void setSkuSubtitle(String skuSubtitle) {
    this.skuSubtitle = skuSubtitle;
  }

  /**
   * 价格
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * 价格
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * 销量
   */
  public Long getSaleCount() {
    return saleCount;
  }

  /**
   * 销量
   */
  public void setSaleCount(Long saleCount) {
    this.saleCount = saleCount;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    SkuInfoEntity other = (SkuInfoEntity) that;
    return (this.getSkuId() == null ? other.getSkuId() == null
        : this.getSkuId().equals(other.getSkuId()))
        && (this.getSpuId() == null ? other.getSpuId() == null
        : this.getSpuId().equals(other.getSpuId()))
        && (this.getSkuName() == null ? other.getSkuName() == null
        : this.getSkuName().equals(other.getSkuName()))
        && (this.getSkuDesc() == null ? other.getSkuDesc() == null
        : this.getSkuDesc().equals(other.getSkuDesc()))
        && (this.getCatalogId() == null ? other.getCatalogId() == null
        : this.getCatalogId().equals(other.getCatalogId()))
        && (this.getBrandId() == null ? other.getBrandId() == null
        : this.getBrandId().equals(other.getBrandId()))
        && (this.getSkuDefaultImg() == null ? other.getSkuDefaultImg() == null
        : this.getSkuDefaultImg().equals(other.getSkuDefaultImg()))
        && (this.getSkuTitle() == null ? other.getSkuTitle() == null
        : this.getSkuTitle().equals(other.getSkuTitle()))
        && (this.getSkuSubtitle() == null ? other.getSkuSubtitle() == null
        : this.getSkuSubtitle().equals(other.getSkuSubtitle()))
        && (this.getPrice() == null ? other.getPrice() == null
        : this.getPrice().equals(other.getPrice()))
        && (this.getSaleCount() == null ? other.getSaleCount() == null
        : this.getSaleCount().equals(other.getSaleCount()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
    result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
    result = prime * result + ((getSkuName() == null) ? 0 : getSkuName().hashCode());
    result = prime * result + ((getSkuDesc() == null) ? 0 : getSkuDesc().hashCode());
    result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
    result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
    result = prime * result + ((getSkuDefaultImg() == null) ? 0 : getSkuDefaultImg().hashCode());
    result = prime * result + ((getSkuTitle() == null) ? 0 : getSkuTitle().hashCode());
    result = prime * result + ((getSkuSubtitle() == null) ? 0 : getSkuSubtitle().hashCode());
    result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
    result = prime * result + ((getSaleCount() == null) ? 0 : getSaleCount().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", skuId=").append(skuId);
    sb.append(", spuId=").append(spuId);
    sb.append(", skuName=").append(skuName);
    sb.append(", skuDesc=").append(skuDesc);
    sb.append(", catalogId=").append(catalogId);
    sb.append(", brandId=").append(brandId);
    sb.append(", skuDefaultImg=").append(skuDefaultImg);
    sb.append(", skuTitle=").append(skuTitle);
    sb.append(", skuSubtitle=").append(skuSubtitle);
    sb.append(", price=").append(price);
    sb.append(", saleCount=").append(saleCount);
    sb.append("]");
    return sb.toString();
  }
}