package com.atguigu.gulimall.order.entity;

import java.util.Objects;

public class Tesdfdsggfa {

  private Long id;
  private String judge;
  private Boolean tesdt;


  @Override
  public String toString() {
    return "Tesdfdsggfa{" +
        "id=" + id +
        ", judge='" + judge + '\'' +
        ", tesdt=" + tesdt +
        '}';
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Tesdfdsggfa that = (Tesdfdsggfa) object;
    return Objects.equals(id, that.id) && Objects.equals(judge, that.judge)
        && Objects.equals(tesdt, that.tesdt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, judge, tesdt);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getJudge() {
    return judge;
  }

  public void setJudge(String judge) {
    this.judge = judge;
  }

  public Boolean getTesdt() {
    return tesdt;
  }

  public void setTesdt(Boolean tesdt) {
    this.tesdt = tesdt;
  }
}
