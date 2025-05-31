package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

/**
 * 商品会员价格
 *
 * @TableName sms_member_price
 */
@TableName(value = "sms_member_price")
public class MemberPriceEntity {

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
   * 会员等级id
   */
  private Long memberLevelId;

  /**
   * 会员等级名
   */
  private String memberLevelName;

  /**
   * 会员对应价格
   */
  private BigDecimal memberPrice;

  /**
   * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
   */
  private Integer addOther;

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
   * 会员等级id
   */
  public Long getMemberLevelId() {
    return memberLevelId;
  }

  /**
   * 会员等级id
   */
  public void setMemberLevelId(Long memberLevelId) {
    this.memberLevelId = memberLevelId;
  }

  /**
   * 会员等级名
   */
  public String getMemberLevelName() {
    return memberLevelName;
  }

  /**
   * 会员等级名
   */
  public void setMemberLevelName(String memberLevelName) {
    this.memberLevelName = memberLevelName;
  }

  /**
   * 会员对应价格
   */
  public BigDecimal getMemberPrice() {
    return memberPrice;
  }

  /**
   * 会员对应价格
   */
  public void setMemberPrice(BigDecimal memberPrice) {
    this.memberPrice = memberPrice;
  }

  /**
   * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
   */
  public Integer getAddOther() {
    return addOther;
  }

  /**
   * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
   */
  public void setAddOther(Integer addOther) {
    this.addOther = addOther;
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
    MemberPriceEntity other = (MemberPriceEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getSkuId() == null ? other.getSkuId() == null
        : this.getSkuId().equals(other.getSkuId()))
        && (this.getMemberLevelId() == null ? other.getMemberLevelId() == null
        : this.getMemberLevelId().equals(other.getMemberLevelId()))
        && (this.getMemberLevelName() == null ? other.getMemberLevelName() == null
        : this.getMemberLevelName().equals(other.getMemberLevelName()))
        && (this.getMemberPrice() == null ? other.getMemberPrice() == null
        : this.getMemberPrice().equals(other.getMemberPrice()))
        && (this.getAddOther() == null ? other.getAddOther() == null
        : this.getAddOther().equals(other.getAddOther()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
    result = prime * result + ((getMemberLevelId() == null) ? 0 : getMemberLevelId().hashCode());
    result =
        prime * result + ((getMemberLevelName() == null) ? 0 : getMemberLevelName().hashCode());
    result = prime * result + ((getMemberPrice() == null) ? 0 : getMemberPrice().hashCode());
    result = prime * result + ((getAddOther() == null) ? 0 : getAddOther().hashCode());
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
    sb.append(", memberLevelId=").append(memberLevelId);
    sb.append(", memberLevelName=").append(memberLevelName);
    sb.append(", memberPrice=").append(memberPrice);
    sb.append(", addOther=").append(addOther);
    sb.append("]");
    return sb.toString();
  }
}