package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * spu属性值
 * @TableName pms_product_attr_value
 */
@TableName(value ="pms_product_attr_value")
public class ProductAttrValueEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 顺序
     */
    private Integer attrSort;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    private Integer quickShow;

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
     * 商品id
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * 商品id
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
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
     * 属性名
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * 属性名
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * 属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * 属性值
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    /**
     * 顺序
     */
    public Integer getAttrSort() {
        return attrSort;
    }

    /**
     * 顺序
     */
    public void setAttrSort(Integer attrSort) {
        this.attrSort = attrSort;
    }

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    public Integer getQuickShow() {
        return quickShow;
    }

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    public void setQuickShow(Integer quickShow) {
        this.quickShow = quickShow;
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
        ProductAttrValueEntity other = (ProductAttrValueEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
            && (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(other.getAttrId()))
            && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(other.getAttrName()))
            && (this.getAttrValue() == null ? other.getAttrValue() == null : this.getAttrValue().equals(other.getAttrValue()))
            && (this.getAttrSort() == null ? other.getAttrSort() == null : this.getAttrSort().equals(other.getAttrSort()))
            && (this.getQuickShow() == null ? other.getQuickShow() == null : this.getQuickShow().equals(other.getQuickShow()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getAttrValue() == null) ? 0 : getAttrValue().hashCode());
        result = prime * result + ((getAttrSort() == null) ? 0 : getAttrSort().hashCode());
        result = prime * result + ((getQuickShow() == null) ? 0 : getQuickShow().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spuId=").append(spuId);
        sb.append(", attrId=").append(attrId);
        sb.append(", attrName=").append(attrName);
        sb.append(", attrValue=").append(attrValue);
        sb.append(", attrSort=").append(attrSort);
        sb.append(", quickShow=").append(quickShow);
        sb.append("]");
        return sb.toString();
    }
}