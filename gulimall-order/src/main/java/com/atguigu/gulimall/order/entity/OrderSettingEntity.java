package com.atguigu.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单配置信息
 *
 * @TableName oms_order_setting
 */
@TableName(value = "oms_order_setting")
public class OrderSettingEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 秒杀订单超时关闭时间(分)
   */
  private Integer flashOrderOvertime;

  /**
   * 正常订单超时时间(分)
   */
  private Integer normalOrderOvertime;

  /**
   * 发货后自动确认收货时间（天）
   */
  private Integer confirmOvertime;

  /**
   * 自动完成交易时间，不能申请退货（天）
   */
  private Integer finishOvertime;

  /**
   * 订单完成后自动好评时间（天）
   */
  private Integer commentOvertime;

  /**
   * 会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】
   */
  private Integer memberLevel;

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
   * 秒杀订单超时关闭时间(分)
   */
  public Integer getFlashOrderOvertime() {
    return flashOrderOvertime;
  }

  /**
   * 秒杀订单超时关闭时间(分)
   */
  public void setFlashOrderOvertime(Integer flashOrderOvertime) {
    this.flashOrderOvertime = flashOrderOvertime;
  }

  /**
   * 正常订单超时时间(分)
   */
  public Integer getNormalOrderOvertime() {
    return normalOrderOvertime;
  }

  /**
   * 正常订单超时时间(分)
   */
  public void setNormalOrderOvertime(Integer normalOrderOvertime) {
    this.normalOrderOvertime = normalOrderOvertime;
  }

  /**
   * 发货后自动确认收货时间（天）
   */
  public Integer getConfirmOvertime() {
    return confirmOvertime;
  }

  /**
   * 发货后自动确认收货时间（天）
   */
  public void setConfirmOvertime(Integer confirmOvertime) {
    this.confirmOvertime = confirmOvertime;
  }

  /**
   * 自动完成交易时间，不能申请退货（天）
   */
  public Integer getFinishOvertime() {
    return finishOvertime;
  }

  /**
   * 自动完成交易时间，不能申请退货（天）
   */
  public void setFinishOvertime(Integer finishOvertime) {
    this.finishOvertime = finishOvertime;
  }

  /**
   * 订单完成后自动好评时间（天）
   */
  public Integer getCommentOvertime() {
    return commentOvertime;
  }

  /**
   * 订单完成后自动好评时间（天）
   */
  public void setCommentOvertime(Integer commentOvertime) {
    this.commentOvertime = commentOvertime;
  }

  /**
   * 会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】
   */
  public Integer getMemberLevel() {
    return memberLevel;
  }

  /**
   * 会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】
   */
  public void setMemberLevel(Integer memberLevel) {
    this.memberLevel = memberLevel;
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
    OrderSettingEntity other = (OrderSettingEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getFlashOrderOvertime() == null ? other.getFlashOrderOvertime() == null
        : this.getFlashOrderOvertime().equals(other.getFlashOrderOvertime()))
        && (this.getNormalOrderOvertime() == null ? other.getNormalOrderOvertime() == null
        : this.getNormalOrderOvertime().equals(other.getNormalOrderOvertime()))
        && (this.getConfirmOvertime() == null ? other.getConfirmOvertime() == null
        : this.getConfirmOvertime().equals(other.getConfirmOvertime()))
        && (this.getFinishOvertime() == null ? other.getFinishOvertime() == null
        : this.getFinishOvertime().equals(other.getFinishOvertime()))
        && (this.getCommentOvertime() == null ? other.getCommentOvertime() == null
        : this.getCommentOvertime().equals(other.getCommentOvertime()))
        && (this.getMemberLevel() == null ? other.getMemberLevel() == null
        : this.getMemberLevel().equals(other.getMemberLevel()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getFlashOrderOvertime() == null) ? 0
        : getFlashOrderOvertime().hashCode());
    result = prime * result + ((getNormalOrderOvertime() == null) ? 0
        : getNormalOrderOvertime().hashCode());
    result =
        prime * result + ((getConfirmOvertime() == null) ? 0 : getConfirmOvertime().hashCode());
    result = prime * result + ((getFinishOvertime() == null) ? 0 : getFinishOvertime().hashCode());
    result =
        prime * result + ((getCommentOvertime() == null) ? 0 : getCommentOvertime().hashCode());
    result = prime * result + ((getMemberLevel() == null) ? 0 : getMemberLevel().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", flashOrderOvertime=").append(flashOrderOvertime);
    sb.append(", normalOrderOvertime=").append(normalOrderOvertime);
    sb.append(", confirmOvertime=").append(confirmOvertime);
    sb.append(", finishOvertime=").append(finishOvertime);
    sb.append(", commentOvertime=").append(commentOvertime);
    sb.append(", memberLevel=").append(memberLevel);
    sb.append("]");
    return sb.toString();
  }
}