package com.atguigu.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 仓库信息
 *
 * @TableName wms_ware_info
 */
@TableName(value = "wms_ware_info")
public class WareInfoEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 仓库名
   */
  private String name;

  /**
   * 仓库地址
   */
  private String address;

  /**
   * 区域编码
   */
  private String areacode;

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
   * 仓库名
   */
  public String getName() {
    return name;
  }

  /**
   * 仓库名
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 仓库地址
   */
  public String getAddress() {
    return address;
  }

  /**
   * 仓库地址
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * 区域编码
   */
  public String getAreacode() {
    return areacode;
  }

  /**
   * 区域编码
   */
  public void setAreacode(String areacode) {
    this.areacode = areacode;
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
    WareInfoEntity other = (WareInfoEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
        : this.getName().equals(other.getName()))
        && (this.getAddress() == null ? other.getAddress() == null
        : this.getAddress().equals(other.getAddress()))
        && (this.getAreacode() == null ? other.getAreacode() == null
        : this.getAreacode().equals(other.getAreacode()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
    result = prime * result + ((getAreacode() == null) ? 0 : getAreacode().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append(", address=").append(address);
    sb.append(", areacode=").append(areacode);
    sb.append("]");
    return sb.toString();
  }
}