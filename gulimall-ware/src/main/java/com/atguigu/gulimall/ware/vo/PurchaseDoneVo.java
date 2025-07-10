package com.atguigu.gulimall.ware.vo;

import java.util.List;

public class PurchaseDoneVo {

  private Long id;
  private List<Item> items;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public static class Item {

    private Long itemId;
    private Long status;
    private String reason;


    public Long getItemId() {
      return itemId;
    }

    public void setItemId(Long itemId) {
      this.itemId = itemId;
    }

    public Long getStatus() {
      return status;
    }

    public void setStatus(Long status) {
      this.status = status;
    }

    public String getReason() {
      return reason;
    }

    public void setReason(String reason) {
      this.reason = reason;
    }
  }

}
