package to.ware;

import java.time.LocalDateTime;
import java.util.List;

public class WareTo {


  /**
   * id
   */
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
  private LocalDateTime createTime;

  /**
   * 仓库id
   */
  private Long wareId;

  /**
   * 工作单备注
   */
  private String taskComment;

  private List<WareItemTo> wareItemToList;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOrderSn() {
    return orderSn;
  }

  public void setOrderSn(String orderSn) {
    this.orderSn = orderSn;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public String getConsigneeTel() {
    return consigneeTel;
  }

  public void setConsigneeTel(String consigneeTel) {
    this.consigneeTel = consigneeTel;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(String deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getOrderComment() {
    return orderComment;
  }

  public void setOrderComment(String orderComment) {
    this.orderComment = orderComment;
  }

  public Integer getPaymentWay() {
    return paymentWay;
  }

  public void setPaymentWay(Integer paymentWay) {
    this.paymentWay = paymentWay;
  }

  public Integer getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(Integer taskStatus) {
    this.taskStatus = taskStatus;
  }

  public String getOrderBody() {
    return orderBody;
  }

  public void setOrderBody(String orderBody) {
    this.orderBody = orderBody;
  }

  public String getTrackingNo() {
    return trackingNo;
  }

  public void setTrackingNo(String trackingNo) {
    this.trackingNo = trackingNo;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public Long getWareId() {
    return wareId;
  }

  public void setWareId(Long wareId) {
    this.wareId = wareId;
  }

  public String getTaskComment() {
    return taskComment;
  }

  public void setTaskComment(String taskComment) {
    this.taskComment = taskComment;
  }

  public List<WareItemTo> getWareItemToList() {
    return wareItemToList;
  }

  public void setWareItemToList(List<WareItemTo> wareItemToList) {
    this.wareItemToList = wareItemToList;
  }
}
