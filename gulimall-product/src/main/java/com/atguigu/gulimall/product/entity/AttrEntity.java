package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品属性
 *
 * @TableName pms_attr
 */
@TableName(value = "pms_attr")
public class AttrEntity {
    /**
     * 属性id
     */
    @TableId(type = IdType.AUTO)
    private Long attrId;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;

    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    private Integer valueType;

    /**
     * 属性图标
     */
    private String icon;

    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性
     */
    private Integer attrType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;

    /**
     * 所属分类
     */
    private Long catalogId;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;

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
     * 是否需要检索[0-不需要，1-需要]
     */
    public Integer getSearchType() {
        return searchType;
    }

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    public Integer getValueType() {
        return valueType;
    }

    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    /**
     * 属性图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 属性图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 可选值列表[用逗号分隔]
     */
    public String getValueSelect() {
        return valueSelect;
    }

    /**
     * 可选值列表[用逗号分隔]
     */
    public void setValueSelect(String valueSelect) {
        this.valueSelect = valueSelect;
    }

    /**
     * 属性类型[0-销售属性，1-基本属性
     */
    public Integer getAttrType() {
        return attrType;
    }

    /**
     * 属性类型[0-销售属性，1-基本属性
     */
    public void setAttrType(Integer attrType) {
        this.attrType = attrType;
    }

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    public Long getEnable() {
        return enable;
    }

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    public void setEnable(Long enable) {
        this.enable = enable;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    public Integer getShowDesc() {
        return showDesc;
    }

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    public void setShowDesc(Integer showDesc) {
        this.showDesc = showDesc;
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
        AttrEntity other = (AttrEntity) that;
        return (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(other.getAttrId()))
                && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(other.getAttrName()))
                && (this.getSearchType() == null ? other.getSearchType() == null : this.getSearchType().equals(other.getSearchType()))
                && (this.getValueType() == null ? other.getValueType() == null : this.getValueType().equals(other.getValueType()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getValueSelect() == null ? other.getValueSelect() == null : this.getValueSelect().equals(other.getValueSelect()))
                && (this.getAttrType() == null ? other.getAttrType() == null : this.getAttrType().equals(other.getAttrType()))
                && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
                && (this.getCatalogId() == null ? other.getCatalogId() == null : this.getCatalogId().equals(other.getCatalogId()))
                && (this.getShowDesc() == null ? other.getShowDesc() == null : this.getShowDesc().equals(other.getShowDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getSearchType() == null) ? 0 : getSearchType().hashCode());
        result = prime * result + ((getValueType() == null) ? 0 : getValueType().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getValueSelect() == null) ? 0 : getValueSelect().hashCode());
        result = prime * result + ((getAttrType() == null) ? 0 : getAttrType().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
        result = prime * result + ((getShowDesc() == null) ? 0 : getShowDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attrId=").append(attrId);
        sb.append(", attrName=").append(attrName);
        sb.append(", searchType=").append(searchType);
        sb.append(", valueType=").append(valueType);
        sb.append(", icon=").append(icon);
        sb.append(", valueSelect=").append(valueSelect);
        sb.append(", attrType=").append(attrType);
        sb.append(", enable=").append(enable);
        sb.append(", catalogId=").append(catalogId);
        sb.append(", showDesc=").append(showDesc);
        sb.append("]");
        return sb.toString();
    }
}