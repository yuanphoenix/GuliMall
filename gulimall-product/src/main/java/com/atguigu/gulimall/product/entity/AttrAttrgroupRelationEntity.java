package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 属性&属性分组关联
 *
 * @TableName pms_attr_attrgroup_relation
 */
@TableName(value = "pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 属性id
   */
  private Long attrId;

  /**
   * 属性分组id
   */
  private Long attrGroupId;

  /**
   * 属性组内排序
   */
  private Integer attrSort;

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
   * 属性id
   */
  public Long getAttrId() {
    return attrId;
  }

  /**
   * 属性id
   */
  public void setAttrId(Long attrId) {
    this.attrId = attrId;
  }

  /**
   * 属性分组id
   */
  public Long getAttrGroupId() {
    return attrGroupId;
  }

  /**
   * 属性分组id
   */
  public void setAttrGroupId(Long attrGroupId) {
    this.attrGroupId = attrGroupId;
  }

  /**
   * 属性组内排序
   */
  public Integer getAttrSort() {
    return attrSort;
  }

  /**
   * 属性组内排序
   */
  public void setAttrSort(Integer attrSort) {
    this.attrSort = attrSort;
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
    AttrAttrgroupRelationEntity other = (AttrAttrgroupRelationEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getAttrId() == null ? other.getAttrId() == null
        : this.getAttrId().equals(other.getAttrId()))
        && (this.getAttrGroupId() == null ? other.getAttrGroupId() == null
        : this.getAttrGroupId().equals(other.getAttrGroupId()))
        && (this.getAttrSort() == null ? other.getAttrSort() == null
        : this.getAttrSort().equals(other.getAttrSort()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
    result = prime * result + ((getAttrGroupId() == null) ? 0 : getAttrGroupId().hashCode());
    result = prime * result + ((getAttrSort() == null) ? 0 : getAttrSort().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", attrId=").append(attrId);
    sb.append(", attrGroupId=").append(attrGroupId);
    sb.append(", attrSort=").append(attrSort);
    sb.append("]");
    return sb.toString();
  }
}