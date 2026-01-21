package com.atguigu.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付信息表
 *
 * @TableName oms_payment_info
 */
@TableName(value = "oms_payment_info")
public class PaymentInfoEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 订单号（对外业务号）
   */
  private String orderSn;

  /**
   * 订单id
   */
  private Long orderId;

  /**
   * 支付宝交易流水号
   */
  private String alipayTradeNo;

  /**
   * 支付总金额
   */
  private BigDecimal totalAmount;

  /**
   * 交易内容
   */
  private String subject;

  /**
   * 支付状态
   */
  private String paymentStatus;

  /**
   * 创建时间
   */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /**
   * 确认时间
   */
  private LocalDateTime confirmTime;

  /**
   * 回调内容
   */
  private String callbackContent;

  /**
   * 回调时间
   */
  private LocalDateTime callbackTime;

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
   * 订单号（对外业务号）
   */
  public String getOrderSn() {
    return orderSn;
  }

  /**
   * 订单号（对外业务号）
   */
  public void setOrderSn(String orderSn) {
    this.orderSn = orderSn;
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
   * 支付宝交易流水号
   */
  public String getAlipayTradeNo() {
    return alipayTradeNo;
  }

  /**
   * 支付宝交易流水号
   */
  public void setAlipayTradeNo(String alipayTradeNo) {
    this.alipayTradeNo = alipayTradeNo;
  }

  /**
   * 支付总金额
   */
  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  /**
   * 支付总金额
   */
  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  /**
   * 交易内容
   */
  public String getSubject() {
    return subject;
  }

  /**
   * 交易内容
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * 支付状态
   */
  public String getPaymentStatus() {
    return paymentStatus;
  }

  /**
   * 支付状态
   */
  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
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
   * 确认时间
   */
  public LocalDateTime getConfirmTime() {
    return confirmTime;
  }

  /**
   * 确认时间
   */
  public void setConfirmTime(LocalDateTime confirmTime) {
    this.confirmTime = confirmTime;
  }

  /**
   * 回调内容
   */
  public String getCallbackContent() {
    return callbackContent;
  }

  /**
   * 回调内容
   */
  public void setCallbackContent(String callbackContent) {
    this.callbackContent = callbackContent;
  }

  /**
   * 回调时间
   */
  public LocalDateTime getCallbackTime() {
    return callbackTime;
  }

  /**
   * 回调时间
   */
  public void setCallbackTime(LocalDateTime callbackTime) {
    this.callbackTime = callbackTime;
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
    PaymentInfoEntity other = (PaymentInfoEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getOrderSn() == null ? other.getOrderSn() == null
        : this.getOrderSn().equals(other.getOrderSn()))
        && (this.getOrderId() == null ? other.getOrderId() == null
        : this.getOrderId().equals(other.getOrderId()))
        && (this.getAlipayTradeNo() == null ? other.getAlipayTradeNo() == null
        : this.getAlipayTradeNo().equals(other.getAlipayTradeNo()))
        && (this.getTotalAmount() == null ? other.getTotalAmount() == null
        : this.getTotalAmount().equals(other.getTotalAmount()))
        && (this.getSubject() == null ? other.getSubject() == null
        : this.getSubject().equals(other.getSubject()))
        && (this.getPaymentStatus() == null ? other.getPaymentStatus() == null
        : this.getPaymentStatus().equals(other.getPaymentStatus()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getConfirmTime() == null ? other.getConfirmTime() == null
        : this.getConfirmTime().equals(other.getConfirmTime()))
        && (this.getCallbackContent() == null ? other.getCallbackContent() == null
        : this.getCallbackContent().equals(other.getCallbackContent()))
        && (this.getCallbackTime() == null ? other.getCallbackTime() == null
        : this.getCallbackTime().equals(other.getCallbackTime()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
    result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
    result = prime * result + ((getAlipayTradeNo() == null) ? 0 : getAlipayTradeNo().hashCode());
    result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
    result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
    result = prime * result + ((getPaymentStatus() == null) ? 0 : getPaymentStatus().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
    result =
        prime * result + ((getCallbackContent() == null) ? 0 : getCallbackContent().hashCode());
    result = prime * result + ((getCallbackTime() == null) ? 0 : getCallbackTime().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", orderSn=").append(orderSn);
    sb.append(", orderId=").append(orderId);
    sb.append(", alipayTradeNo=").append(alipayTradeNo);
    sb.append(", totalAmount=").append(totalAmount);
    sb.append(", subject=").append(subject);
    sb.append(", paymentStatus=").append(paymentStatus);
    sb.append(", createTime=").append(createTime);
    sb.append(", confirmTime=").append(confirmTime);
    sb.append(", callbackContent=").append(callbackContent);
    sb.append(", callbackTime=").append(callbackTime);
    sb.append("]");
    return sb.toString();
  }
}