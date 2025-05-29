package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 优惠券与产品关联
 *
 * @TableName sms_coupon_spu_relation
 */
@TableName(value = "sms_coupon_spu_relation")
public class CouponSpuRelationEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * spu_id
     */
    private Long spuId;

    /**
     * spu_name
     */
    private String spuName;

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
     * 优惠券id
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * 优惠券id
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * spu_id
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * spu_id
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    /**
     * spu_name
     */
    public String getSpuName() {
        return spuName;
    }

    /**
     * spu_name
     */
    public void setSpuName(String spuName) {
        this.spuName = spuName;
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
        CouponSpuRelationEntity other = (CouponSpuRelationEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCouponId() == null ? other.getCouponId() == null : this.getCouponId().equals(other.getCouponId()))
                && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
                && (this.getSpuName() == null ? other.getSpuName() == null : this.getSpuName().equals(other.getSpuName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getSpuName() == null) ? 0 : getSpuName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponId=").append(couponId);
        sb.append(", spuId=").append(spuId);
        sb.append(", spuName=").append(spuName);
        sb.append("]");
        return sb.toString();
    }
}