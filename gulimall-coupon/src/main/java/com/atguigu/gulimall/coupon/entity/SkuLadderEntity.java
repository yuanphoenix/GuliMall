package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

/**
 * 商品阶梯价格
 * @TableName sms_sku_ladder
 */
@TableName(value ="sms_sku_ladder")
public class SkuLadderEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * spu_id
     */
    private Long skuId;

    /**
     * 满几件
     */
    private Integer fullCount;

    /**
     * 打几折
     */
    private BigDecimal discount;

    /**
     * 折后价
     */
    private BigDecimal price;

    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
    private Integer addOther;

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
     * spu_id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * spu_id
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * 满几件
     */
    public Integer getFullCount() {
        return fullCount;
    }

    /**
     * 满几件
     */
    public void setFullCount(Integer fullCount) {
        this.fullCount = fullCount;
    }

    /**
     * 打几折
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * 打几折
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 折后价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 折后价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
    public Integer getAddOther() {
        return addOther;
    }

    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
    public void setAddOther(Integer addOther) {
        this.addOther = addOther;
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
        SkuLadderEntity other = (SkuLadderEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getFullCount() == null ? other.getFullCount() == null : this.getFullCount().equals(other.getFullCount()))
            && (this.getDiscount() == null ? other.getDiscount() == null : this.getDiscount().equals(other.getDiscount()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getAddOther() == null ? other.getAddOther() == null : this.getAddOther().equals(other.getAddOther()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getFullCount() == null) ? 0 : getFullCount().hashCode());
        result = prime * result + ((getDiscount() == null) ? 0 : getDiscount().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getAddOther() == null) ? 0 : getAddOther().hashCode());
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
        sb.append(", fullCount=").append(fullCount);
        sb.append(", discount=").append(discount);
        sb.append(", price=").append(price);
        sb.append(", addOther=").append(addOther);
        sb.append("]");
        return sb.toString();
    }
}