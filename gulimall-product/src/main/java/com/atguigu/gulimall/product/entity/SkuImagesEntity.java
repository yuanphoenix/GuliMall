package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * sku图片
 *
 * @TableName pms_sku_images
 */
@TableName(value = "pms_sku_images")
public class SkuImagesEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * sku_id
   */
  private Long skuId;

  /**
   * 图片地址
   */
  private String imgUrl;

  /**
   * 排序
   */
  private Integer imgSort;

  /**
   * 默认图[0 - 不是默认图，1 - 是默认图]
   */
  private Integer defaultImg;

  /**
   * id
   */
  public Long getId() {
    return id;
  }

  /**
   * id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * sku_id
   */
  public Long getSkuId() {
    return skuId;
  }

  /**
   * sku_id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 图片地址
   */
  public String getImgUrl() {
    return imgUrl;
  }

  /**
   * 图片地址
   */
  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  /**
   * 排序
   */
  public Integer getImgSort() {
    return imgSort;
  }

  /**
   * 排序
   */
  public void setImgSort(Integer imgSort) {
    this.imgSort = imgSort;
  }

  /**
   * 默认图[0 - 不是默认图，1 - 是默认图]
   */
  public Integer getDefaultImg() {
    return defaultImg;
  }

  /**
   * 默认图[0 - 不是默认图，1 - 是默认图]
   */
  public void setDefaultImg(Integer defaultImg) {
    this.defaultImg = defaultImg;
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
    SkuImagesEntity other = (SkuImagesEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getSkuId() == null ? other.getSkuId() == null
        : this.getSkuId().equals(other.getSkuId()))
        && (this.getImgUrl() == null ? other.getImgUrl() == null
        : this.getImgUrl().equals(other.getImgUrl()))
        && (this.getImgSort() == null ? other.getImgSort() == null
        : this.getImgSort().equals(other.getImgSort()))
        && (this.getDefaultImg() == null ? other.getDefaultImg() == null
        : this.getDefaultImg().equals(other.getDefaultImg()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
    result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
    result = prime * result + ((getImgSort() == null) ? 0 : getImgSort().hashCode());
    result = prime * result + ((getDefaultImg() == null) ? 0 : getDefaultImg().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", skuId=").append(skuId);
    sb.append(", imgUrl=").append(imgUrl);
    sb.append(", imgSort=").append(imgSort);
    sb.append(", defaultImg=").append(defaultImg);
    sb.append("]");
    return sb.toString();
  }
}