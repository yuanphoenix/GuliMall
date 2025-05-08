package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * sku销售属性&值
 * @TableName pms_sku_sale_attr_value
 */
@TableName(value ="pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * attr_id
     */
    private Long attrId;

    /**
     * 销售属性名
     */
    private String attrName;

    /**
     * 销售属性值
     */
    private String attrValue;

    /**
     * 顺序
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
     * sku_id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * sku_id
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * attr_id
     */
    public Long getAttrId() {
        return attrId;
    }

    /**
     * attr_id
     */
    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    /**
     * 销售属性名
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * 销售属性名
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * 销售属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * 销售属性值
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
        SkuSaleAttrValueEntity other = (SkuSaleAttrValueEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(other.getAttrId()))
            && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(other.getAttrName()))
            && (this.getAttrValue() == null ? other.getAttrValue() == null : this.getAttrValue().equals(other.getAttrValue()))
            && (this.getAttrSort() == null ? other.getAttrSort() == null : this.getAttrSort().equals(other.getAttrSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getAttrValue() == null) ? 0 : getAttrValue().hashCode());
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
        sb.append(", skuId=").append(skuId);
        sb.append(", attrId=").append(attrId);
        sb.append(", attrName=").append(attrName);
        sb.append(", attrValue=").append(attrValue);
        sb.append(", attrSort=").append(attrSort);
        sb.append("]");
        return sb.toString();
    }
}