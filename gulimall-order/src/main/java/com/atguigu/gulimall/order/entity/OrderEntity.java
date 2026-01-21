package com.atguigu.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @TableName oms_order
 */
@TableName(value = "oms_order")
public class OrderEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * member_id
   */
  private Long memberId;

  /**
   * 订单号
   */
  private String orderSn;

  /**
   * 使用的优惠券
   */
  private Long couponId;

  /**
   * create_time
   */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /**
   * 用户名
   */
  private String memberUsername;

  /**
   * 订单总额
   */
  private BigDecimal totalAmount;

  /**
   * 应付总额
   */
  private BigDecimal payAmount;

  /**
   * 运费金额
   */
  private BigDecimal freightAmount;

  /**
   * 促销优化金额（促销价、满减、阶梯价）
   */
  private BigDecimal promotionAmount;

  /**
   * 积分抵扣金额
   */
  private BigDecimal integrationAmount;

  /**
   * 优惠券抵扣金额
   */
  private BigDecimal couponAmount;

  /**
   * 后台调整订单使用的折扣金额
   */
  private BigDecimal discountAmount;

  /**
   * 支付方式【PAYPAL中国->101；PAYPAL香港->102；PAYPAL全球->103；支付宝->201；支付宝香港->202；支付宝全球->203；微信->301；微信香港->302；微信全球->303；银联->401；货到付款->501】
   */
  private Integer payType;

  /**
   * 订单来源[0->PC订单；1->app订单]
   */
  private Integer sourceType;

  /**
   * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
   */
  private Integer status;

  /**
   * 物流公司(配送方式)
   */
  private String deliveryCompany;

  /**
   * 物流单号
   */
  private String deliverySn;

  /**
   * 自动确认时间（天）
   */
  private Integer autoConfirmDay;

  /**
   * 可以获得的积分
   */
  private Integer integration;

  /**
   * 可以获得的成长值
   */
  private Integer growth;

  /**
   * 发票类型[0->不开发票；1->电子发票；2->纸质发票]
   */
  private Integer billType;

  /**
   * 发票抬头
   */
  private String billHeader;

  /**
   * 发票内容
   */
  private String billContent;

  /**
   * 收票人电话
   */
  private String billReceiverPhone;

  /**
   * 收票人邮箱
   */
  private String billReceiverEmail;

  /**
   * 收货人姓名
   */
  private String receiverName;

  /**
   * 收货人电话
   */
  private String receiverPhone;

  /**
   * 收货人邮编
   */
  private String receiverPostCode;

  /**
   * 省份/直辖市
   */
  private String receiverProvince;

  /**
   * 城市
   */
  private String receiverCity;

  /**
   * 区
   */
  private String receiverRegion;

  /**
   * 详细地址
   */
  private String receiverDetailAddress;

  /**
   * 订单备注
   */
  private String note;

  /**
   * 确认收货状态[0->未确认；1->已确认]
   */
  private Integer confirmStatus;

  /**
   * 删除状态【0->未删除；1->已删除】
   */
  private Integer deleteStatus;

  /**
   * 下单时使用的积分
   */
  private Integer useIntegration;

  /**
   * 支付时间
   */
  private LocalDateTime paymentTime;

  /**
   * 发货时间
   */
  private LocalDateTime deliveryTime;

  /**
   * 确认收货时间
   */
  private LocalDateTime receiveTime;

  /**
   * 评价时间
   */
  private LocalDateTime commentTime;

  /**
   * 修改时间
   */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime modifyTime;

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
   * member_id
   */
  public Long getMemberId() {
    return memberId;
  }

  /**
   * member_id
   */
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  /**
   * 订单号
   */
  public String getOrderSn() {
    return orderSn;
  }

  /**
   * 订单号
   */
  public void setOrderSn(String orderSn) {
    this.orderSn = orderSn;
  }

  /**
   * 使用的优惠券
   */
  public Long getCouponId() {
    return couponId;
  }

  /**
   * 使用的优惠券
   */
  public void setCouponId(Long couponId) {
    this.couponId = couponId;
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
   * 用户名
   */
  public String getMemberUsername() {
    return memberUsername;
  }

  /**
   * 用户名
   */
  public void setMemberUsername(String memberUsername) {
    this.memberUsername = memberUsername;
  }

  /**
   * 订单总额
   */
  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  /**
   * 订单总额
   */
  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  /**
   * 应付总额
   */
  public BigDecimal getPayAmount() {
    return payAmount;
  }

  /**
   * 应付总额
   */
  public void setPayAmount(BigDecimal payAmount) {
    this.payAmount = payAmount;
  }

  /**
   * 运费金额
   */
  public BigDecimal getFreightAmount() {
    return freightAmount;
  }

  /**
   * 运费金额
   */
  public void setFreightAmount(BigDecimal freightAmount) {
    this.freightAmount = freightAmount;
  }

  /**
   * 促销优化金额（促销价、满减、阶梯价）
   */
  public BigDecimal getPromotionAmount() {
    return promotionAmount;
  }

  /**
   * 促销优化金额（促销价、满减、阶梯价）
   */
  public void setPromotionAmount(BigDecimal promotionAmount) {
    this.promotionAmount = promotionAmount;
  }

  /**
   * 积分抵扣金额
   */
  public BigDecimal getIntegrationAmount() {
    return integrationAmount;
  }

  /**
   * 积分抵扣金额
   */
  public void setIntegrationAmount(BigDecimal integrationAmount) {
    this.integrationAmount = integrationAmount;
  }

  /**
   * 优惠券抵扣金额
   */
  public BigDecimal getCouponAmount() {
    return couponAmount;
  }

  /**
   * 优惠券抵扣金额
   */
  public void setCouponAmount(BigDecimal couponAmount) {
    this.couponAmount = couponAmount;
  }

  /**
   * 后台调整订单使用的折扣金额
   */
  public BigDecimal getDiscountAmount() {
    return discountAmount;
  }

  /**
   * 后台调整订单使用的折扣金额
   */
  public void setDiscountAmount(BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 支付方式【PAYPAL中国->101；PAYPAL香港->102；PAYPAL全球->103；支付宝->201；支付宝香港->202；支付宝全球->203；微信->301；微信香港->302；微信全球->303；银联->401；货到付款->501】
   */
  public Integer getPayType() {
    return payType;
  }

  /**
   * 支付方式【PAYPAL中国->101；PAYPAL香港->102；PAYPAL全球->103；支付宝->201；支付宝香港->202；支付宝全球->203；微信->301；微信香港->302；微信全球->303；银联->401；货到付款->501】
   */
  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  /**
   * 订单来源[0->PC订单；1->app订单]
   */
  public Integer getSourceType() {
    return sourceType;
  }

  /**
   * 订单来源[0->PC订单；1->app订单]
   */
  public void setSourceType(Integer sourceType) {
    this.sourceType = sourceType;
  }

  /**
   * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * 物流公司(配送方式)
   */
  public String getDeliveryCompany() {
    return deliveryCompany;
  }

  /**
   * 物流公司(配送方式)
   */
  public void setDeliveryCompany(String deliveryCompany) {
    this.deliveryCompany = deliveryCompany;
  }

  /**
   * 物流单号
   */
  public String getDeliverySn() {
    return deliverySn;
  }

  /**
   * 物流单号
   */
  public void setDeliverySn(String deliverySn) {
    this.deliverySn = deliverySn;
  }

  /**
   * 自动确认时间（天）
   */
  public Integer getAutoConfirmDay() {
    return autoConfirmDay;
  }

  /**
   * 自动确认时间（天）
   */
  public void setAutoConfirmDay(Integer autoConfirmDay) {
    this.autoConfirmDay = autoConfirmDay;
  }

  /**
   * 可以获得的积分
   */
  public Integer getIntegration() {
    return integration;
  }

  /**
   * 可以获得的积分
   */
  public void setIntegration(Integer integration) {
    this.integration = integration;
  }

  /**
   * 可以获得的成长值
   */
  public Integer getGrowth() {
    return growth;
  }

  /**
   * 可以获得的成长值
   */
  public void setGrowth(Integer growth) {
    this.growth = growth;
  }

  /**
   * 发票类型[0->不开发票；1->电子发票；2->纸质发票]
   */
  public Integer getBillType() {
    return billType;
  }

  /**
   * 发票类型[0->不开发票；1->电子发票；2->纸质发票]
   */
  public void setBillType(Integer billType) {
    this.billType = billType;
  }

  /**
   * 发票抬头
   */
  public String getBillHeader() {
    return billHeader;
  }

  /**
   * 发票抬头
   */
  public void setBillHeader(String billHeader) {
    this.billHeader = billHeader;
  }

  /**
   * 发票内容
   */
  public String getBillContent() {
    return billContent;
  }

  /**
   * 发票内容
   */
  public void setBillContent(String billContent) {
    this.billContent = billContent;
  }

  /**
   * 收票人电话
   */
  public String getBillReceiverPhone() {
    return billReceiverPhone;
  }

  /**
   * 收票人电话
   */
  public void setBillReceiverPhone(String billReceiverPhone) {
    this.billReceiverPhone = billReceiverPhone;
  }

  /**
   * 收票人邮箱
   */
  public String getBillReceiverEmail() {
    return billReceiverEmail;
  }

  /**
   * 收票人邮箱
   */
  public void setBillReceiverEmail(String billReceiverEmail) {
    this.billReceiverEmail = billReceiverEmail;
  }

  /**
   * 收货人姓名
   */
  public String getReceiverName() {
    return receiverName;
  }

  /**
   * 收货人姓名
   */
  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  /**
   * 收货人电话
   */
  public String getReceiverPhone() {
    return receiverPhone;
  }

  /**
   * 收货人电话
   */
  public void setReceiverPhone(String receiverPhone) {
    this.receiverPhone = receiverPhone;
  }

  /**
   * 收货人邮编
   */
  public String getReceiverPostCode() {
    return receiverPostCode;
  }

  /**
   * 收货人邮编
   */
  public void setReceiverPostCode(String receiverPostCode) {
    this.receiverPostCode = receiverPostCode;
  }

  /**
   * 省份/直辖市
   */
  public String getReceiverProvince() {
    return receiverProvince;
  }

  /**
   * 省份/直辖市
   */
  public void setReceiverProvince(String receiverProvince) {
    this.receiverProvince = receiverProvince;
  }

  /**
   * 城市
   */
  public String getReceiverCity() {
    return receiverCity;
  }

  /**
   * 城市
   */
  public void setReceiverCity(String receiverCity) {
    this.receiverCity = receiverCity;
  }

  /**
   * 区
   */
  public String getReceiverRegion() {
    return receiverRegion;
  }

  /**
   * 区
   */
  public void setReceiverRegion(String receiverRegion) {
    this.receiverRegion = receiverRegion;
  }

  /**
   * 详细地址
   */
  public String getReceiverDetailAddress() {
    return receiverDetailAddress;
  }

  /**
   * 详细地址
   */
  public void setReceiverDetailAddress(String receiverDetailAddress) {
    this.receiverDetailAddress = receiverDetailAddress;
  }

  /**
   * 订单备注
   */
  public String getNote() {
    return note;
  }

  /**
   * 订单备注
   */
  public void setNote(String note) {
    this.note = note;
  }

  /**
   * 确认收货状态[0->未确认；1->已确认]
   */
  public Integer getConfirmStatus() {
    return confirmStatus;
  }

  /**
   * 确认收货状态[0->未确认；1->已确认]
   */
  public void setConfirmStatus(Integer confirmStatus) {
    this.confirmStatus = confirmStatus;
  }

  /**
   * 删除状态【0->未删除；1->已删除】
   */
  public Integer getDeleteStatus() {
    return deleteStatus;
  }

  /**
   * 删除状态【0->未删除；1->已删除】
   */
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  /**
   * 下单时使用的积分
   */
  public Integer getUseIntegration() {
    return useIntegration;
  }

  /**
   * 下单时使用的积分
   */
  public void setUseIntegration(Integer useIntegration) {
    this.useIntegration = useIntegration;
  }

  /**
   * 支付时间
   */
  public LocalDateTime getPaymentTime() {
    return paymentTime;
  }

  /**
   * 支付时间
   */
  public void setPaymentTime(LocalDateTime paymentTime) {
    this.paymentTime = paymentTime;
  }

  /**
   * 发货时间
   */
  public LocalDateTime getDeliveryTime() {
    return deliveryTime;
  }

  /**
   * 发货时间
   */
  public void setDeliveryTime(LocalDateTime deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

  /**
   * 确认收货时间
   */
  public LocalDateTime getReceiveTime() {
    return receiveTime;
  }

  /**
   * 确认收货时间
   */
  public void setReceiveTime(LocalDateTime receiveTime) {
    this.receiveTime = receiveTime;
  }

  /**
   * 评价时间
   */
  public LocalDateTime getCommentTime() {
    return commentTime;
  }

  /**
   * 评价时间
   */
  public void setCommentTime(LocalDateTime commentTime) {
    this.commentTime = commentTime;
  }

  /**
   * 修改时间
   */
  public LocalDateTime getModifyTime() {
    return modifyTime;
  }

  /**
   * 修改时间
   */
  public void setModifyTime(LocalDateTime modifyTime) {
    this.modifyTime = modifyTime;
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
    OrderEntity other = (OrderEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getMemberId() == null ? other.getMemberId() == null
        : this.getMemberId().equals(other.getMemberId()))
        && (this.getOrderSn() == null ? other.getOrderSn() == null
        : this.getOrderSn().equals(other.getOrderSn()))
        && (this.getCouponId() == null ? other.getCouponId() == null
        : this.getCouponId().equals(other.getCouponId()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getMemberUsername() == null ? other.getMemberUsername() == null
        : this.getMemberUsername().equals(other.getMemberUsername()))
        && (this.getTotalAmount() == null ? other.getTotalAmount() == null
        : this.getTotalAmount().equals(other.getTotalAmount()))
        && (this.getPayAmount() == null ? other.getPayAmount() == null
        : this.getPayAmount().equals(other.getPayAmount()))
        && (this.getFreightAmount() == null ? other.getFreightAmount() == null
        : this.getFreightAmount().equals(other.getFreightAmount()))
        && (this.getPromotionAmount() == null ? other.getPromotionAmount() == null
        : this.getPromotionAmount().equals(other.getPromotionAmount()))
        && (this.getIntegrationAmount() == null ? other.getIntegrationAmount() == null
        : this.getIntegrationAmount().equals(other.getIntegrationAmount()))
        && (this.getCouponAmount() == null ? other.getCouponAmount() == null
        : this.getCouponAmount().equals(other.getCouponAmount()))
        && (this.getDiscountAmount() == null ? other.getDiscountAmount() == null
        : this.getDiscountAmount().equals(other.getDiscountAmount()))
        && (this.getPayType() == null ? other.getPayType() == null
        : this.getPayType().equals(other.getPayType()))
        && (this.getSourceType() == null ? other.getSourceType() == null
        : this.getSourceType().equals(other.getSourceType()))
        && (this.getStatus() == null ? other.getStatus() == null
        : this.getStatus().equals(other.getStatus()))
        && (this.getDeliveryCompany() == null ? other.getDeliveryCompany() == null
        : this.getDeliveryCompany().equals(other.getDeliveryCompany()))
        && (this.getDeliverySn() == null ? other.getDeliverySn() == null
        : this.getDeliverySn().equals(other.getDeliverySn()))
        && (this.getAutoConfirmDay() == null ? other.getAutoConfirmDay() == null
        : this.getAutoConfirmDay().equals(other.getAutoConfirmDay()))
        && (this.getIntegration() == null ? other.getIntegration() == null
        : this.getIntegration().equals(other.getIntegration()))
        && (this.getGrowth() == null ? other.getGrowth() == null
        : this.getGrowth().equals(other.getGrowth()))
        && (this.getBillType() == null ? other.getBillType() == null
        : this.getBillType().equals(other.getBillType()))
        && (this.getBillHeader() == null ? other.getBillHeader() == null
        : this.getBillHeader().equals(other.getBillHeader()))
        && (this.getBillContent() == null ? other.getBillContent() == null
        : this.getBillContent().equals(other.getBillContent()))
        && (this.getBillReceiverPhone() == null ? other.getBillReceiverPhone() == null
        : this.getBillReceiverPhone().equals(other.getBillReceiverPhone()))
        && (this.getBillReceiverEmail() == null ? other.getBillReceiverEmail() == null
        : this.getBillReceiverEmail().equals(other.getBillReceiverEmail()))
        && (this.getReceiverName() == null ? other.getReceiverName() == null
        : this.getReceiverName().equals(other.getReceiverName()))
        && (this.getReceiverPhone() == null ? other.getReceiverPhone() == null
        : this.getReceiverPhone().equals(other.getReceiverPhone()))
        && (this.getReceiverPostCode() == null ? other.getReceiverPostCode() == null
        : this.getReceiverPostCode().equals(other.getReceiverPostCode()))
        && (this.getReceiverProvince() == null ? other.getReceiverProvince() == null
        : this.getReceiverProvince().equals(other.getReceiverProvince()))
        && (this.getReceiverCity() == null ? other.getReceiverCity() == null
        : this.getReceiverCity().equals(other.getReceiverCity()))
        && (this.getReceiverRegion() == null ? other.getReceiverRegion() == null
        : this.getReceiverRegion().equals(other.getReceiverRegion()))
        && (this.getReceiverDetailAddress() == null ? other.getReceiverDetailAddress() == null
        : this.getReceiverDetailAddress().equals(other.getReceiverDetailAddress()))
        && (this.getNote() == null ? other.getNote() == null
        : this.getNote().equals(other.getNote()))
        && (this.getConfirmStatus() == null ? other.getConfirmStatus() == null
        : this.getConfirmStatus().equals(other.getConfirmStatus()))
        && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null
        : this.getDeleteStatus().equals(other.getDeleteStatus()))
        && (this.getUseIntegration() == null ? other.getUseIntegration() == null
        : this.getUseIntegration().equals(other.getUseIntegration()))
        && (this.getPaymentTime() == null ? other.getPaymentTime() == null
        : this.getPaymentTime().equals(other.getPaymentTime()))
        && (this.getDeliveryTime() == null ? other.getDeliveryTime() == null
        : this.getDeliveryTime().equals(other.getDeliveryTime()))
        && (this.getReceiveTime() == null ? other.getReceiveTime() == null
        : this.getReceiveTime().equals(other.getReceiveTime()))
        && (this.getCommentTime() == null ? other.getCommentTime() == null
        : this.getCommentTime().equals(other.getCommentTime()))
        && (this.getModifyTime() == null ? other.getModifyTime() == null
        : this.getModifyTime().equals(other.getModifyTime()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
    result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
    result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getMemberUsername() == null) ? 0 : getMemberUsername().hashCode());
    result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
    result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
    result = prime * result + ((getFreightAmount() == null) ? 0 : getFreightAmount().hashCode());
    result =
        prime * result + ((getPromotionAmount() == null) ? 0 : getPromotionAmount().hashCode());
    result =
        prime * result + ((getIntegrationAmount() == null) ? 0 : getIntegrationAmount().hashCode());
    result = prime * result + ((getCouponAmount() == null) ? 0 : getCouponAmount().hashCode());
    result = prime * result + ((getDiscountAmount() == null) ? 0 : getDiscountAmount().hashCode());
    result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
    result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
    result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
    result =
        prime * result + ((getDeliveryCompany() == null) ? 0 : getDeliveryCompany().hashCode());
    result = prime * result + ((getDeliverySn() == null) ? 0 : getDeliverySn().hashCode());
    result = prime * result + ((getAutoConfirmDay() == null) ? 0 : getAutoConfirmDay().hashCode());
    result = prime * result + ((getIntegration() == null) ? 0 : getIntegration().hashCode());
    result = prime * result + ((getGrowth() == null) ? 0 : getGrowth().hashCode());
    result = prime * result + ((getBillType() == null) ? 0 : getBillType().hashCode());
    result = prime * result + ((getBillHeader() == null) ? 0 : getBillHeader().hashCode());
    result = prime * result + ((getBillContent() == null) ? 0 : getBillContent().hashCode());
    result =
        prime * result + ((getBillReceiverPhone() == null) ? 0 : getBillReceiverPhone().hashCode());
    result =
        prime * result + ((getBillReceiverEmail() == null) ? 0 : getBillReceiverEmail().hashCode());
    result = prime * result + ((getReceiverName() == null) ? 0 : getReceiverName().hashCode());
    result = prime * result + ((getReceiverPhone() == null) ? 0 : getReceiverPhone().hashCode());
    result =
        prime * result + ((getReceiverPostCode() == null) ? 0 : getReceiverPostCode().hashCode());
    result =
        prime * result + ((getReceiverProvince() == null) ? 0 : getReceiverProvince().hashCode());
    result = prime * result + ((getReceiverCity() == null) ? 0 : getReceiverCity().hashCode());
    result = prime * result + ((getReceiverRegion() == null) ? 0 : getReceiverRegion().hashCode());
    result = prime * result + ((getReceiverDetailAddress() == null) ? 0
        : getReceiverDetailAddress().hashCode());
    result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
    result = prime * result + ((getConfirmStatus() == null) ? 0 : getConfirmStatus().hashCode());
    result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
    result = prime * result + ((getUseIntegration() == null) ? 0 : getUseIntegration().hashCode());
    result = prime * result + ((getPaymentTime() == null) ? 0 : getPaymentTime().hashCode());
    result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
    result = prime * result + ((getReceiveTime() == null) ? 0 : getReceiveTime().hashCode());
    result = prime * result + ((getCommentTime() == null) ? 0 : getCommentTime().hashCode());
    result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", memberId=").append(memberId);
    sb.append(", orderSn=").append(orderSn);
    sb.append(", couponId=").append(couponId);
    sb.append(", createTime=").append(createTime);
    sb.append(", memberUsername=").append(memberUsername);
    sb.append(", totalAmount=").append(totalAmount);
    sb.append(", payAmount=").append(payAmount);
    sb.append(", freightAmount=").append(freightAmount);
    sb.append(", promotionAmount=").append(promotionAmount);
    sb.append(", integrationAmount=").append(integrationAmount);
    sb.append(", couponAmount=").append(couponAmount);
    sb.append(", discountAmount=").append(discountAmount);
    sb.append(", payType=").append(payType);
    sb.append(", sourceType=").append(sourceType);
    sb.append(", status=").append(status);
    sb.append(", deliveryCompany=").append(deliveryCompany);
    sb.append(", deliverySn=").append(deliverySn);
    sb.append(", autoConfirmDay=").append(autoConfirmDay);
    sb.append(", integration=").append(integration);
    sb.append(", growth=").append(growth);
    sb.append(", billType=").append(billType);
    sb.append(", billHeader=").append(billHeader);
    sb.append(", billContent=").append(billContent);
    sb.append(", billReceiverPhone=").append(billReceiverPhone);
    sb.append(", billReceiverEmail=").append(billReceiverEmail);
    sb.append(", receiverName=").append(receiverName);
    sb.append(", receiverPhone=").append(receiverPhone);
    sb.append(", receiverPostCode=").append(receiverPostCode);
    sb.append(", receiverProvince=").append(receiverProvince);
    sb.append(", receiverCity=").append(receiverCity);
    sb.append(", receiverRegion=").append(receiverRegion);
    sb.append(", receiverDetailAddress=").append(receiverDetailAddress);
    sb.append(", note=").append(note);
    sb.append(", confirmStatus=").append(confirmStatus);
    sb.append(", deleteStatus=").append(deleteStatus);
    sb.append(", useIntegration=").append(useIntegration);
    sb.append(", paymentTime=").append(paymentTime);
    sb.append(", deliveryTime=").append(deliveryTime);
    sb.append(", receiveTime=").append(receiveTime);
    sb.append(", commentTime=").append(commentTime);
    sb.append(", modifyTime=").append(modifyTime);
    sb.append("]");
    return sb.toString();
  }
}