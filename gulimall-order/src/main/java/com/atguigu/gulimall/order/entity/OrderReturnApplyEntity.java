package com.atguigu.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单退货申请
 *
 * @TableName oms_order_return_apply
 */
@TableName(value = "oms_order_return_apply")
public class OrderReturnApplyEntity {

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
   * 退货商品id
   */
  private Long skuId;

  /**
   * 订单编号
   */
  private String orderSn;

  /**
   * 申请时间
   */
  private LocalDateTime createTime;

  /**
   * 会员用户名
   */
  private String memberUsername;

  /**
   * 退款金额
   */
  private BigDecimal returnAmount;

  /**
   * 退货人姓名
   */
  private String returnName;

  /**
   * 退货人电话
   */
  private String returnPhone;

  /**
   * 申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]
   */
  private Integer status;

  /**
   * 处理时间
   */
  private LocalDateTime handleTime;

  /**
   * 商品图片
   */
  private String skuImg;

  /**
   * 商品名称
   */
  private String skuName;

  /**
   * 商品品牌
   */
  private String skuBrand;

  /**
   * 商品销售属性(JSON)
   */
  private String skuAttrsVals;

  /**
   * 退货数量
   */
  private Integer skuCount;

  /**
   * 商品单价
   */
  private BigDecimal skuPrice;

  /**
   * 商品实际支付单价
   */
  private BigDecimal skuRealPrice;

  /**
   * 原因
   */
  private String reason;

  /**
   * 描述
   */
  private String description述;

  /**
   * 凭证图片，以逗号隔开
   */
  private String descPics;

  /**
   * 处理备注
   */
  private String handleNote;

  /**
   * 处理人员
   */
  private String handleMan;

  /**
   * 收货人
   */
  private String receiveMan;

  /**
   * 收货时间
   */
  private LocalDateTime receiveTime;

  /**
   * 收货备注
   */
  private String receiveNote;

  /**
   * 收货电话
   */
  private String receivePhone;

  /**
   * 公司收货地址
   */
  private String companyAddress;

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
   * 退货商品id
   */
  public Long getSkuId() {
    return skuId;
  }

  /**
   * 退货商品id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 订单编号
   */
  public String getOrderSn() {
    return orderSn;
  }

  /**
   * 订单编号
   */
  public void setOrderSn(String orderSn) {
    this.orderSn = orderSn;
  }

  /**
   * 申请时间
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * 申请时间
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  /**
   * 会员用户名
   */
  public String getMemberUsername() {
    return memberUsername;
  }

  /**
   * 会员用户名
   */
  public void setMemberUsername(String memberUsername) {
    this.memberUsername = memberUsername;
  }

  /**
   * 退款金额
   */
  public BigDecimal getReturnAmount() {
    return returnAmount;
  }

  /**
   * 退款金额
   */
  public void setReturnAmount(BigDecimal returnAmount) {
    this.returnAmount = returnAmount;
  }

  /**
   * 退货人姓名
   */
  public String getReturnName() {
    return returnName;
  }

  /**
   * 退货人姓名
   */
  public void setReturnName(String returnName) {
    this.returnName = returnName;
  }

  /**
   * 退货人电话
   */
  public String getReturnPhone() {
    return returnPhone;
  }

  /**
   * 退货人电话
   */
  public void setReturnPhone(String returnPhone) {
    this.returnPhone = returnPhone;
  }

  /**
   * 申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * 处理时间
   */
  public LocalDateTime getHandleTime() {
    return handleTime;
  }

  /**
   * 处理时间
   */
  public void setHandleTime(LocalDateTime handleTime) {
    this.handleTime = handleTime;
  }

  /**
   * 商品图片
   */
  public String getSkuImg() {
    return skuImg;
  }

  /**
   * 商品图片
   */
  public void setSkuImg(String skuImg) {
    this.skuImg = skuImg;
  }

  /**
   * 商品名称
   */
  public String getSkuName() {
    return skuName;
  }

  /**
   * 商品名称
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  /**
   * 商品品牌
   */
  public String getSkuBrand() {
    return skuBrand;
  }

  /**
   * 商品品牌
   */
  public void setSkuBrand(String skuBrand) {
    this.skuBrand = skuBrand;
  }

  /**
   * 商品销售属性(JSON)
   */
  public String getSkuAttrsVals() {
    return skuAttrsVals;
  }

  /**
   * 商品销售属性(JSON)
   */
  public void setSkuAttrsVals(String skuAttrsVals) {
    this.skuAttrsVals = skuAttrsVals;
  }

  /**
   * 退货数量
   */
  public Integer getSkuCount() {
    return skuCount;
  }

  /**
   * 退货数量
   */
  public void setSkuCount(Integer skuCount) {
    this.skuCount = skuCount;
  }

  /**
   * 商品单价
   */
  public BigDecimal getSkuPrice() {
    return skuPrice;
  }

  /**
   * 商品单价
   */
  public void setSkuPrice(BigDecimal skuPrice) {
    this.skuPrice = skuPrice;
  }

  /**
   * 商品实际支付单价
   */
  public BigDecimal getSkuRealPrice() {
    return skuRealPrice;
  }

  /**
   * 商品实际支付单价
   */
  public void setSkuRealPrice(BigDecimal skuRealPrice) {
    this.skuRealPrice = skuRealPrice;
  }

  /**
   * 原因
   */
  public String getReason() {
    return reason;
  }

  /**
   * 原因
   */
  public void setReason(String reason) {
    this.reason = reason;
  }

  /**
   * 描述
   */
  public String getDescription述() {
    return description述;
  }

  /**
   * 描述
   */
  public void setDescription述(String description述) {
    this.description述 = description述;
  }

  /**
   * 凭证图片，以逗号隔开
   */
  public String getDescPics() {
    return descPics;
  }

  /**
   * 凭证图片，以逗号隔开
   */
  public void setDescPics(String descPics) {
    this.descPics = descPics;
  }

  /**
   * 处理备注
   */
  public String getHandleNote() {
    return handleNote;
  }

  /**
   * 处理备注
   */
  public void setHandleNote(String handleNote) {
    this.handleNote = handleNote;
  }

  /**
   * 处理人员
   */
  public String getHandleMan() {
    return handleMan;
  }

  /**
   * 处理人员
   */
  public void setHandleMan(String handleMan) {
    this.handleMan = handleMan;
  }

  /**
   * 收货人
   */
  public String getReceiveMan() {
    return receiveMan;
  }

  /**
   * 收货人
   */
  public void setReceiveMan(String receiveMan) {
    this.receiveMan = receiveMan;
  }

  /**
   * 收货时间
   */
  public LocalDateTime getReceiveTime() {
    return receiveTime;
  }

  /**
   * 收货时间
   */
  public void setReceiveTime(LocalDateTime receiveTime) {
    this.receiveTime = receiveTime;
  }

  /**
   * 收货备注
   */
  public String getReceiveNote() {
    return receiveNote;
  }

  /**
   * 收货备注
   */
  public void setReceiveNote(String receiveNote) {
    this.receiveNote = receiveNote;
  }

  /**
   * 收货电话
   */
  public String getReceivePhone() {
    return receivePhone;
  }

  /**
   * 收货电话
   */
  public void setReceivePhone(String receivePhone) {
    this.receivePhone = receivePhone;
  }

  /**
   * 公司收货地址
   */
  public String getCompanyAddress() {
    return companyAddress;
  }

  /**
   * 公司收货地址
   */
  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
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
    OrderReturnApplyEntity other = (OrderReturnApplyEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getOrderId() == null ? other.getOrderId() == null
        : this.getOrderId().equals(other.getOrderId()))
        && (this.getSkuId() == null ? other.getSkuId() == null
        : this.getSkuId().equals(other.getSkuId()))
        && (this.getOrderSn() == null ? other.getOrderSn() == null
        : this.getOrderSn().equals(other.getOrderSn()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getMemberUsername() == null ? other.getMemberUsername() == null
        : this.getMemberUsername().equals(other.getMemberUsername()))
        && (this.getReturnAmount() == null ? other.getReturnAmount() == null
        : this.getReturnAmount().equals(other.getReturnAmount()))
        && (this.getReturnName() == null ? other.getReturnName() == null
        : this.getReturnName().equals(other.getReturnName()))
        && (this.getReturnPhone() == null ? other.getReturnPhone() == null
        : this.getReturnPhone().equals(other.getReturnPhone()))
        && (this.getStatus() == null ? other.getStatus() == null
        : this.getStatus().equals(other.getStatus()))
        && (this.getHandleTime() == null ? other.getHandleTime() == null
        : this.getHandleTime().equals(other.getHandleTime()))
        && (this.getSkuImg() == null ? other.getSkuImg() == null
        : this.getSkuImg().equals(other.getSkuImg()))
        && (this.getSkuName() == null ? other.getSkuName() == null
        : this.getSkuName().equals(other.getSkuName()))
        && (this.getSkuBrand() == null ? other.getSkuBrand() == null
        : this.getSkuBrand().equals(other.getSkuBrand()))
        && (this.getSkuAttrsVals() == null ? other.getSkuAttrsVals() == null
        : this.getSkuAttrsVals().equals(other.getSkuAttrsVals()))
        && (this.getSkuCount() == null ? other.getSkuCount() == null
        : this.getSkuCount().equals(other.getSkuCount()))
        && (this.getSkuPrice() == null ? other.getSkuPrice() == null
        : this.getSkuPrice().equals(other.getSkuPrice()))
        && (this.getSkuRealPrice() == null ? other.getSkuRealPrice() == null
        : this.getSkuRealPrice().equals(other.getSkuRealPrice()))
        && (this.getReason() == null ? other.getReason() == null
        : this.getReason().equals(other.getReason()))
        && (this.getDescription述() == null ? other.getDescription述() == null
        : this.getDescription述().equals(other.getDescription述()))
        && (this.getDescPics() == null ? other.getDescPics() == null
        : this.getDescPics().equals(other.getDescPics()))
        && (this.getHandleNote() == null ? other.getHandleNote() == null
        : this.getHandleNote().equals(other.getHandleNote()))
        && (this.getHandleMan() == null ? other.getHandleMan() == null
        : this.getHandleMan().equals(other.getHandleMan()))
        && (this.getReceiveMan() == null ? other.getReceiveMan() == null
        : this.getReceiveMan().equals(other.getReceiveMan()))
        && (this.getReceiveTime() == null ? other.getReceiveTime() == null
        : this.getReceiveTime().equals(other.getReceiveTime()))
        && (this.getReceiveNote() == null ? other.getReceiveNote() == null
        : this.getReceiveNote().equals(other.getReceiveNote()))
        && (this.getReceivePhone() == null ? other.getReceivePhone() == null
        : this.getReceivePhone().equals(other.getReceivePhone()))
        && (this.getCompanyAddress() == null ? other.getCompanyAddress() == null
        : this.getCompanyAddress().equals(other.getCompanyAddress()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
    result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
    result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getMemberUsername() == null) ? 0 : getMemberUsername().hashCode());
    result = prime * result + ((getReturnAmount() == null) ? 0 : getReturnAmount().hashCode());
    result = prime * result + ((getReturnName() == null) ? 0 : getReturnName().hashCode());
    result = prime * result + ((getReturnPhone() == null) ? 0 : getReturnPhone().hashCode());
    result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
    result = prime * result + ((getHandleTime() == null) ? 0 : getHandleTime().hashCode());
    result = prime * result + ((getSkuImg() == null) ? 0 : getSkuImg().hashCode());
    result = prime * result + ((getSkuName() == null) ? 0 : getSkuName().hashCode());
    result = prime * result + ((getSkuBrand() == null) ? 0 : getSkuBrand().hashCode());
    result = prime * result + ((getSkuAttrsVals() == null) ? 0 : getSkuAttrsVals().hashCode());
    result = prime * result + ((getSkuCount() == null) ? 0 : getSkuCount().hashCode());
    result = prime * result + ((getSkuPrice() == null) ? 0 : getSkuPrice().hashCode());
    result = prime * result + ((getSkuRealPrice() == null) ? 0 : getSkuRealPrice().hashCode());
    result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
    result = prime * result + ((getDescription述() == null) ? 0 : getDescription述().hashCode());
    result = prime * result + ((getDescPics() == null) ? 0 : getDescPics().hashCode());
    result = prime * result + ((getHandleNote() == null) ? 0 : getHandleNote().hashCode());
    result = prime * result + ((getHandleMan() == null) ? 0 : getHandleMan().hashCode());
    result = prime * result + ((getReceiveMan() == null) ? 0 : getReceiveMan().hashCode());
    result = prime * result + ((getReceiveTime() == null) ? 0 : getReceiveTime().hashCode());
    result = prime * result + ((getReceiveNote() == null) ? 0 : getReceiveNote().hashCode());
    result = prime * result + ((getReceivePhone() == null) ? 0 : getReceivePhone().hashCode());
    result = prime * result + ((getCompanyAddress() == null) ? 0 : getCompanyAddress().hashCode());
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
    sb.append(", skuId=").append(skuId);
    sb.append(", orderSn=").append(orderSn);
    sb.append(", createTime=").append(createTime);
    sb.append(", memberUsername=").append(memberUsername);
    sb.append(", returnAmount=").append(returnAmount);
    sb.append(", returnName=").append(returnName);
    sb.append(", returnPhone=").append(returnPhone);
    sb.append(", status=").append(status);
    sb.append(", handleTime=").append(handleTime);
    sb.append(", skuImg=").append(skuImg);
    sb.append(", skuName=").append(skuName);
    sb.append(", skuBrand=").append(skuBrand);
    sb.append(", skuAttrsVals=").append(skuAttrsVals);
    sb.append(", skuCount=").append(skuCount);
    sb.append(", skuPrice=").append(skuPrice);
    sb.append(", skuRealPrice=").append(skuRealPrice);
    sb.append(", reason=").append(reason);
    sb.append(", description述=").append(description述);
    sb.append(", descPics=").append(descPics);
    sb.append(", handleNote=").append(handleNote);
    sb.append(", handleMan=").append(handleMan);
    sb.append(", receiveMan=").append(receiveMan);
    sb.append(", receiveTime=").append(receiveTime);
    sb.append(", receiveNote=").append(receiveNote);
    sb.append(", receivePhone=").append(receivePhone);
    sb.append(", companyAddress=").append(companyAddress);
    sb.append("]");
    return sb.toString();
  }
}