package com.atguigu.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 库存工作单
 *
 * @TableName wms_ware_order_task
 */
@TableName(value = "wms_ware_order_task")
public class WareOrderTaskEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * order_id
   */
  private Long orderId;

  /**
   * order_sn
   */
  private String orderSn;

  /**
   * 收货人
   */
  private String consignee;

  /**
   * 收货人电话
   */
  private String consigneeTel;

  /**
   * 配送地址
   */
  private String deliveryAddress;

  /**
   * 订单备注
   */
  private String orderComment;

  /**
   * 付款方式【 1:在线付款 2:货到付款】
   */
  private Integer paymentWay;

  /**
   * 任务状态
   */
  private Integer taskStatus;

  /**
   * 订单描述
   */
  private String orderBody;

  /**
   * 物流单号
   */
  private String trackingNo;

  /**
   * create_time
   */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /**
   * 仓库id
   */
  private Long wareId;

  /**
   * 工作单备注
   */
  private String taskComment;

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
   * order_id
   */
  public Long getOrderId() {
    return orderId;
  }

  /**
   * order_id
   */
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  /**
   * order_sn
   */
  public String getOrderSn() {
    return orderSn;
  }

  /**
   * order_sn
   */
  public void setOrderSn(String orderSn) {
    this.orderSn = orderSn;
  }

  /**
   * 收货人
   */
  public String getConsignee() {
    return consignee;
  }

  /**
   * 收货人
   */
  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  /**
   * 收货人电话
   */
  public String getConsigneeTel() {
    return consigneeTel;
  }

  /**
   * 收货人电话
   */
  public void setConsigneeTel(String consigneeTel) {
    this.consigneeTel = consigneeTel;
  }

  /**
   * 配送地址
   */
  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  /**
   * 配送地址
   */
  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  /**
   * 订单备注
   */
  public String getOrderComment() {
    return orderComment;
  }

  /**
   * 订单备注
   */
  public void setOrderComment(String orderComment) {
    this.orderComment = orderComment;
  }

  /**
   * 付款方式【 1:在线付款 2:货到付款】
   */
  public Integer getPaymentWay() {
    return paymentWay;
  }

  /**
   * 付款方式【 1:在线付款 2:货到付款】
   */
  public void setPaymentWay(Integer paymentWay) {
    this.paymentWay = paymentWay;
  }

  /**
   * 任务状态
   */
  public Integer getTaskStatus() {
    return taskStatus;
  }

  /**
   * 任务状态
   */
  public void setTaskStatus(Integer taskStatus) {
    this.taskStatus = taskStatus;
  }

  /**
   * 订单描述
   */
  public String getOrderBody() {
    return orderBody;
  }

  /**
   * 订单描述
   */
  public void setOrderBody(String orderBody) {
    this.orderBody = orderBody;
  }

  /**
   * 物流单号
   */
  public String getTrackingNo() {
    return trackingNo;
  }

  /**
   * 物流单号
   */
  public void setTrackingNo(String trackingNo) {
    this.trackingNo = trackingNo;
  }

  /**
   * create_time
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * create_time
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  /**
   * 仓库id
   */
  public Long getWareId() {
    return wareId;
  }

  /**
   * 仓库id
   */
  public void setWareId(Long wareId) {
    this.wareId = wareId;
  }

  /**
   * 工作单备注
   */
  public String getTaskComment() {
    return taskComment;
  }

  /**
   * 工作单备注
   */
  public void setTaskComment(String taskComment) {
    this.taskComment = taskComment;
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
    WareOrderTaskEntity other = (WareOrderTaskEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getOrderId() == null ? other.getOrderId() == null
        : this.getOrderId().equals(other.getOrderId()))
        && (this.getOrderSn() == null ? other.getOrderSn() == null
        : this.getOrderSn().equals(other.getOrderSn()))
        && (this.getConsignee() == null ? other.getConsignee() == null
        : this.getConsignee().equals(other.getConsignee()))
        && (this.getConsigneeTel() == null ? other.getConsigneeTel() == null
        : this.getConsigneeTel().equals(other.getConsigneeTel()))
        && (this.getDeliveryAddress() == null ? other.getDeliveryAddress() == null
        : this.getDeliveryAddress().equals(other.getDeliveryAddress()))
        && (this.getOrderComment() == null ? other.getOrderComment() == null
        : this.getOrderComment().equals(other.getOrderComment()))
        && (this.getPaymentWay() == null ? other.getPaymentWay() == null
        : this.getPaymentWay().equals(other.getPaymentWay()))
        && (this.getTaskStatus() == null ? other.getTaskStatus() == null
        : this.getTaskStatus().equals(other.getTaskStatus()))
        && (this.getOrderBody() == null ? other.getOrderBody() == null
        : this.getOrderBody().equals(other.getOrderBody()))
        && (this.getTrackingNo() == null ? other.getTrackingNo() == null
        : this.getTrackingNo().equals(other.getTrackingNo()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getWareId() == null ? other.getWareId() == null
        : this.getWareId().equals(other.getWareId()))
        && (this.getTaskComment() == null ? other.getTaskComment() == null
        : this.getTaskComment().equals(other.getTaskComment()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
    result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
    result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
    result = prime * result + ((getConsigneeTel() == null) ? 0 : getConsigneeTel().hashCode());
    result =
        prime * result + ((getDeliveryAddress() == null) ? 0 : getDeliveryAddress().hashCode());
    result = prime * result + ((getOrderComment() == null) ? 0 : getOrderComment().hashCode());
    result = prime * result + ((getPaymentWay() == null) ? 0 : getPaymentWay().hashCode());
    result = prime * result + ((getTaskStatus() == null) ? 0 : getTaskStatus().hashCode());
    result = prime * result + ((getOrderBody() == null) ? 0 : getOrderBody().hashCode());
    result = prime * result + ((getTrackingNo() == null) ? 0 : getTrackingNo().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getWareId() == null) ? 0 : getWareId().hashCode());
    result = prime * result + ((getTaskComment() == null) ? 0 : getTaskComment().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", orderId=").append(orderId);
    sb.append(", orderSn=").append(orderSn);
    sb.append(", consignee=").append(consignee);
    sb.append(", consigneeTel=").append(consigneeTel);
    sb.append(", deliveryAddress=").append(deliveryAddress);
    sb.append(", orderComment=").append(orderComment);
    sb.append(", paymentWay=").append(paymentWay);
    sb.append(", taskStatus=").append(taskStatus);
    sb.append(", orderBody=").append(orderBody);
    sb.append(", trackingNo=").append(trackingNo);
    sb.append(", createTime=").append(createTime);
    sb.append(", wareId=").append(wareId);
    sb.append(", taskComment=").append(taskComment);
    sb.append("]");
    return sb.toString();
  }
}