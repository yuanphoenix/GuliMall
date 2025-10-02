package com.atguigu.gulimall.product.vo;

import java.util.List;

public class Catelog2Vo {


  private String catalog1Id;
  private String id;
  private String name;

  private List<Catelog3Vo> catalog3List;


  public String getCatalog1Id() {
    return catalog1Id;
  }

  public void setCatalog1Id(String catalog1Id) {
    this.catalog1Id = catalog1Id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Catelog3Vo> getCatalog3List() {
    return catalog3List;
  }

  public void setCatalog3List(
      List<Catelog3Vo> catalog3List) {
    this.catalog3List = catalog3List;
  }

  public static class Catelog3Vo {

    private String catalog2Id;
    private String id;
    private String name;

    public String getCatalog2Id() {
      return catalog2Id;
    }

    public void setCatalog2Id(String catalog2Id) {
      this.catalog2Id = catalog2Id;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
