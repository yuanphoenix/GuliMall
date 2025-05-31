package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 品牌分类关联
 *
 * @TableName pms_category_brand_relation
 */
@TableName(value = "pms_category_brand_relation")
public class CategoryBrandRelationEntity {

  /**
   *
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 品牌id
   */
  private Long brandId;

  /**
   * 分类id
   */
  private Long catalogId;

  /**
   *
   */
  private String brandName;

  /**
   *
   */
  private String catalogName;

  /**
   *
   */
  public Long getId() {
    return id;
  }

  /**
   *
   */
  public void setId(Long id) {
    this.id = id;
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
   *
   */
  public String getBrandName() {
    return brandName;
  }

  /**
   *
   */
  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public Long getCatalogId() {
    return catalogId;
  }

  public void setCatalogId(Long catalogId) {
    this.catalogId = catalogId;
  }

  public String getCatalogName() {
    return catalogName;
  }

  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
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
    CategoryBrandRelationEntity other = (CategoryBrandRelationEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getBrandId() == null ? other.getBrandId() == null
        : this.getBrandId().equals(other.getBrandId()))
        && (this.getCatalogId() == null ? other.getCatalogId() == null
        : this.getCatalogId().equals(other.getCatalogId()))
        && (this.getBrandName() == null ? other.getBrandName() == null
        : this.getBrandName().equals(other.getBrandName()))
        && (this.getCatalogName() == null ? other.getCatalogName() == null
        : this.getCatalogName().equals(other.getCatalogName()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
    result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
    result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
    result = prime * result + ((getCatalogName() == null) ? 0 : getCatalogName().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", brandId=").append(brandId);
    sb.append(", catalogId=").append(catalogId);
    sb.append(", brandName=").append(brandName);
    sb.append(", catalogName=").append(catalogName);
    sb.append("]");
    return sb.toString();
  }
}