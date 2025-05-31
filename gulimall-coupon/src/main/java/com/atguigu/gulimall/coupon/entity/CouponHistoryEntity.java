package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 优惠券领取历史记录
 *
 * @TableName sms_coupon_history
 */
@TableName(value = "sms_coupon_history")
public class CouponHistoryEntity {

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
   * 会员id
   */
  private Long memberId;

  /**
   * 会员名字
   */
  private String memberNickName;

  /**
   * 获取方式[0->后台赠送；1->主动领取]
   */
  private Integer getType;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * 使用状态[0->未使用；1->已使用；2->已过期]
   */
  private Integer useType;

  /**
   * 使用时间
   */
  private LocalDateTime useTime;

  /**
   * 订单id
   */
  private Long orderId;

  /**
   * 订单号
   */
  private Long orderSn;

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
   * 会员id
   */
  public Long getMemberId() {
    return memberId;
  }

  /**
   * 会员id
   */
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  /**
   * 会员名字
   */
  public String getMemberNickName() {
    return memberNickName;
  }

  /**
   * 会员名字
   */
  public void setMemberNickName(String memberNickName) {
    this.memberNickName = memberNickName;
  }

  /**
   * 获取方式[0->后台赠送；1->主动领取]
   */
  public Integer getGetType() {
    return getType;
  }

  /**
   * 获取方式[0->后台赠送；1->主动领取]
   */
  public void setGetType(Integer getType) {
    this.getType = getType;
  }

  /**
   * 创建时间
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * 创建时间
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  /**
   * 使用状态[0->未使用；1->已使用；2->已过期]
   */
  public Integer getUseType() {
    return useType;
  }

  /**
   * 使用状态[0->未使用；1->已使用；2->已过期]
   */
  public void setUseType(Integer useType) {
    this.useType = useType;
  }

  /**
   * 使用时间
   */
  public LocalDateTime getUseTime() {
    return useTime;
  }

  /**
   * 使用时间
   */
  public void setUseTime(LocalDateTime useTime) {
    this.useTime = useTime;
  }

  /**
   * 订单id
   */
  public Long getOrderId() {
    return orderId;
  }

  /**
   * 订单id
   */
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  /**
   * 订单号
   */
  public Long getOrderSn() {
    return orderSn;
  }

  /**
   * 订单号
   */
  public void setOrderSn(Long orderSn) {
    this.orderSn = orderSn;
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
    CouponHistoryEntity other = (CouponHistoryEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getCouponId() == null ? other.getCouponId() == null
        : this.getCouponId().equals(other.getCouponId()))
        && (this.getMemberId() == null ? other.getMemberId() == null
        : this.getMemberId().equals(other.getMemberId()))
        && (this.getMemberNickName() == null ? other.getMemberNickName() == null
        : this.getMemberNickName().equals(other.getMemberNickName()))
        && (this.getGetType() == null ? other.getGetType() == null
        : this.getGetType().equals(other.getGetType()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getUseType() == null ? other.getUseType() == null
        : this.getUseType().equals(other.getUseType()))
        && (this.getUseTime() == null ? other.getUseTime() == null
        : this.getUseTime().equals(other.getUseTime()))
        && (this.getOrderId() == null ? other.getOrderId() == null
        : this.getOrderId().equals(other.getOrderId()))
        && (this.getOrderSn() == null ? other.getOrderSn() == null
        : this.getOrderSn().equals(other.getOrderSn()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
    result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
    result = prime * result + ((getMemberNickName() == null) ? 0 : getMemberNickName().hashCode());
    result = prime * result + ((getGetType() == null) ? 0 : getGetType().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getUseType() == null) ? 0 : getUseType().hashCode());
    result = prime * result + ((getUseTime() == null) ? 0 : getUseTime().hashCode());
    result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
    result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
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
    sb.append(", memberId=").append(memberId);
    sb.append(", memberNickName=").append(memberNickName);
    sb.append(", getType=").append(getType);
    sb.append(", createTime=").append(createTime);
    sb.append(", useType=").append(useType);
    sb.append(", useTime=").append(useTime);
    sb.append(", orderId=").append(orderId);
    sb.append(", orderSn=").append(orderSn);
    sb.append("]");
    return sb.toString();
  }
}