package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import valid.ListValues;
import valid.UpdateGroup;

/**
 * 品牌
 *
 * @TableName pms_brand
 */
@TableName(value = "pms_brand")
public class BrandEntity {

  /**
   * 品牌id
   */
  @NotNull(groups = UpdateGroup.class)
  @TableId(type = IdType.AUTO)
  private Long brandId;

  /**
   * 品牌名
   */

  @NotBlank
  private String name;

  /**
   * 品牌logo地址
   */
  @NotBlank
  @URL
  private String logo;

  /**
   * 介绍
   */
  @NotBlank
  private String descript;

  /**
   * 显示状态[0-不显示；1-显示]
   */
  @ListValues(val = {0, 1}, message = "显示状态必须为 0 或 1")
  private Integer showStatus;

  /**
   * 检索首字母
   */
  @NotBlank
  private String firstLetter;

  /**
   * 排序
   */
  @NotNull
  @Min(0)
  private Integer sort;

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
   * 品牌名
   */
  public String getName() {
    return name;
  }

  /**
   * 品牌名
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 品牌logo地址
   */
  public String getLogo() {
    return logo;
  }

  /**
   * 品牌logo地址
   */
  public void setLogo(String logo) {
    this.logo = logo;
  }

  /**
   * 介绍
   */
  public String getDescript() {
    return descript;
  }

  /**
   * 介绍
   */
  public void setDescript(String descript) {
    this.descript = descript;
  }

  /**
   * 显示状态[0-不显示；1-显示]
   */
  public Integer getShowStatus() {
    return showStatus;
  }

  /**
   * 显示状态[0-不显示；1-显示]
   */
  public void setShowStatus(Integer showStatus) {
    this.showStatus = showStatus;
  }

  /**
   * 检索首字母
   */
  public String getFirstLetter() {
    return firstLetter;
  }

  /**
   * 检索首字母
   */
  public void setFirstLetter(String firstLetter) {
    this.firstLetter = firstLetter;
  }

  /**
   * 排序
   */
  public Integer getSort() {
    return sort;
  }

  /**
   * 排序
   */
  public void setSort(Integer sort) {
    this.sort = sort;
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
    BrandEntity other = (BrandEntity) that;
    return (this.getBrandId() == null ? other.getBrandId() == null
        : this.getBrandId().equals(other.getBrandId()))
        && (this.getName() == null ? other.getName() == null
        : this.getName().equals(other.getName()))
        && (this.getLogo() == null ? other.getLogo() == null
        : this.getLogo().equals(other.getLogo()))
        && (this.getDescript() == null ? other.getDescript() == null
        : this.getDescript().equals(other.getDescript()))
        && (this.getShowStatus() == null ? other.getShowStatus() == null
        : this.getShowStatus().equals(other.getShowStatus()))
        && (this.getFirstLetter() == null ? other.getFirstLetter() == null
        : this.getFirstLetter().equals(other.getFirstLetter()))
        && (this.getSort() == null ? other.getSort() == null
        : this.getSort().equals(other.getSort()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
    result = prime * result + ((getDescript() == null) ? 0 : getDescript().hashCode());
    result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
    result = prime * result + ((getFirstLetter() == null) ? 0 : getFirstLetter().hashCode());
    result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", brandId=").append(brandId);
    sb.append(", name=").append(name);
    sb.append(", logo=").append(logo);
    sb.append(", descript=").append(descript);
    sb.append(", showStatus=").append(showStatus);
    sb.append(", firstLetter=").append(firstLetter);
    sb.append(", sort=").append(sort);
    sb.append("]");
    return sb.toString();
  }
}