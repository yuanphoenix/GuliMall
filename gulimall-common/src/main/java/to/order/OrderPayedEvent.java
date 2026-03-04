package to.order;

public class OrderPayedEvent {

  public OrderPayedEvent() {

  }

  public OrderPayedEvent(String orderSn) {
    this.orderSn = orderSn;
  }

  private String orderSn;

  public String getOrderSn() {
    return orderSn;
  }

  public void setOrderSn(String orderSn) {
    this.orderSn = orderSn;
  }
}
