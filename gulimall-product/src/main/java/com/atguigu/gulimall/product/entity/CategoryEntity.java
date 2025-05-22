package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品三级分类
 *
 * @TableName pms_category
 */
@TableName(value = "pms_category")
public class CategoryEntity {
    /**
     * 分类id
     */
    @TableId(type = IdType.AUTO)
    private Long catId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类id
     */
    private Long parentCid;

    /**
     * 层级
     */
    private Integer catLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    @TableLogic
    private Integer showStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 计量单位
     */
    private String productUnit;

    /**
     * 商品数量
     */
    private Integer productCount;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<CategoryEntity> children;

    @TableField(exist = false)
    private Boolean leaf = Boolean.TRUE;


    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * 分类id
     */
    public Long getCatId() {
        return catId;
    }

    /**
     * 分类id
     */
    public void setCatId(Long catId) {
        this.catId = catId;
    }

    /**
     * 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 父分类id
     */
    public Long getParentCid() {
        return parentCid;
    }

    /**
     * 父分类id
     */
    public void setParentCid(Long parentCid) {
        this.parentCid = parentCid;
    }

    /**
     * 层级
     */
    public Integer getCatLevel() {
        return catLevel;
    }

    /**
     * 层级
     */
    public void setCatLevel(Integer catLevel) {
        this.catLevel = catLevel;
    }

    /**
     * 是否显示[0-不显示，1显示]
     */
    public Integer getShowStatus() {
        return showStatus;
    }

    /**
     * 是否显示[0-不显示，1显示]
     */
    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    /**
     * 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 图标地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 图标地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 计量单位
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     * 计量单位
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * 商品数量
     */
    public Integer getProductCount() {
        return productCount;
    }


    /**
     * 商品数量
     */
    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
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
        CategoryEntity other = (CategoryEntity) that;
        return (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getParentCid() == null ? other.getParentCid() == null : this.getParentCid().equals(other.getParentCid()))
                && (this.getCatLevel() == null ? other.getCatLevel() == null : this.getCatLevel().equals(other.getCatLevel()))
                && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()))
                && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getProductUnit() == null ? other.getProductUnit() == null : this.getProductUnit().equals(other.getProductUnit()))
                && (this.getProductCount() == null ? other.getProductCount() == null : this.getProductCount().equals(other.getProductCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getParentCid() == null) ? 0 : getParentCid().hashCode());
        result = prime * result + ((getCatLevel() == null) ? 0 : getCatLevel().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getProductUnit() == null) ? 0 : getProductUnit().hashCode());
        result = prime * result + ((getProductCount() == null) ? 0 : getProductCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catId=").append(catId);
        sb.append(", name=").append(name);
        sb.append(", parentCid=").append(parentCid);
        sb.append(", catLevel=").append(catLevel);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", sort=").append(sort);
        sb.append(", icon=").append(icon);
        sb.append(", productUnit=").append(productUnit);
        sb.append(", productCount=").append(productCount);
        sb.append("]");
        return sb.toString();
    }

    public List<CategoryEntity> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<CategoryEntity> children) {
        this.children = children;
    }
}