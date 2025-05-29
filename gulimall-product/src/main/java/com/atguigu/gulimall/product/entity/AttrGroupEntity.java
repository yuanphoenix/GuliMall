package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 属性分组
 *
 * @TableName pms_attr_group
 */
@TableName(value = "pms_attr_group")
public class AttrGroupEntity {
    /**
     * 分组id
     */
    @TableId(type = IdType.AUTO)
    private Long attrGroupId;

    /**
     * 组名
     */
    private String attrGroupName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String descript;

    /**
     * 组图标
     */
    private String icon;

    /**
     * 所属分类id
     */
    private Long catalogId;

    /**
     * 分组id
     */
    public Long getAttrGroupId() {
        return attrGroupId;
    }

    /**
     * 分组id
     */
    public void setAttrGroupId(Long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    /**
     * 组名
     */
    public String getAttrGroupName() {
        return attrGroupName;
    }

    /**
     * 组名
     */
    public void setAttrGroupName(String attrGroupName) {
        this.attrGroupName = attrGroupName;
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
     * 描述
     */
    public String getDescript() {
        return descript;
    }

    /**
     * 描述
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * 组图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 组图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
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
        AttrGroupEntity other = (AttrGroupEntity) that;
        return (this.getAttrGroupId() == null ? other.getAttrGroupId() == null : this.getAttrGroupId().equals(other.getAttrGroupId()))
                && (this.getAttrGroupName() == null ? other.getAttrGroupName() == null : this.getAttrGroupName().equals(other.getAttrGroupName()))
                && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                && (this.getDescript() == null ? other.getDescript() == null : this.getDescript().equals(other.getDescript()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getCatalogId() == null ? other.getCatalogId() == null : this.getCatalogId().equals(other.getCatalogId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAttrGroupId() == null) ? 0 : getAttrGroupId().hashCode());
        result = prime * result + ((getAttrGroupName() == null) ? 0 : getAttrGroupName().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getDescript() == null) ? 0 : getDescript().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attrGroupId=").append(attrGroupId);
        sb.append(", attrGroupName=").append(attrGroupName);
        sb.append(", sort=").append(sort);
        sb.append(", descript=").append(descript);
        sb.append(", icon=").append(icon);
        sb.append(", catalogId=").append(catalogId);
        sb.append("]");
        return sb.toString();
    }
}