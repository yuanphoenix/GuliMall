package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 优惠券分类关联
 *
 * @TableName sms_coupon_spu_category_relation
 */
@TableName(value = "sms_coupon_spu_category_relation")
public class CouponSpuCategoryRelationEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 优惠券id
   */
  private Long couponId;

  /**
   * 产品分类id
   */
  private Long categoryId;

  /**
   * 产品分类名称
   */
  private String categoryName;

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
   * 优惠券id
   */
  public Long getCouponId() {
    return couponId;
  }

  /**
   * 优惠券id
   */
  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

  /**
   * 产品分类id
   */
  public Long getCategoryId() {
    return categoryId;
  }

  /**
   * 产品分类id
   */
  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * 产品分类名称
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * 产品分类名称
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
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
    CouponSpuCategoryRelationEntity other = (CouponSpuCategoryRelationEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getCouponId() == null ? other.getCouponId() == null
        : this.getCouponId().equals(other.getCouponId()))
        && (this.getCategoryId() == null ? other.getCategoryId() == null
        : this.getCategoryId().equals(other.getCategoryId()))
        && (this.getCategoryName() == null ? other.getCategoryName() == null
        : this.getCategoryName().equals(other.getCategoryName()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
    result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
    result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", couponId=").append(couponId);
    sb.append(", categoryId=").append(categoryId);
    sb.append(", categoryName=").append(categoryName);
    sb.append("]");
    return sb.toString();
  }
}