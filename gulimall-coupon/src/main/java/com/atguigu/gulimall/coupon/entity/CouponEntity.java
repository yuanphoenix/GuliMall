package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券信息
 *
 * @TableName sms_coupon
 */
@TableName(value = "sms_coupon")
public class CouponEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
     */
    private Integer couponType;

    /**
     * 优惠券图片
     */
    private String couponImg;

    /**
     * 优惠卷名字
     */
    private String couponName;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 每人限领张数
     */
    private Integer perLimit;

    /**
     * 使用门槛
     */
    private BigDecimal minPoint;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 使用类型[0->全场通用；1->指定分类；2->指定商品]
     */
    private Integer useType;

    /**
     * 备注
     */
    private String note;

    /**
     * 发行数量
     */
    private Integer publishCount;

    /**
     * 已使用数量
     */
    private Integer useCount;

    /**
     * 领取数量
     */
    private Integer receiveCount;

    /**
     * 可以领取的开始日期
     */
    private LocalDateTime enableStartTime;

    /**
     * 可以领取的结束日期
     */
    private LocalDateTime enableEndTime;

    /**
     * 优惠码
     */
    private String code;

    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    private Integer memberLevel;

    /**
     * 发布状态[0-未发布，1-已发布]
     */
    private Integer publish;

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
     * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
     */
    public Integer getCouponType() {
        return couponType;
    }

    /**
     * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    /**
     * 优惠券图片
     */
    public String getCouponImg() {
        return couponImg;
    }

    /**
     * 优惠券图片
     */
    public void setCouponImg(String couponImg) {
        this.couponImg = couponImg;
    }

    /**
     * 优惠卷名字
     */
    public String getCouponName() {
        return couponName;
    }

    /**
     * 优惠卷名字
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    /**
     * 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 每人限领张数
     */
    public Integer getPerLimit() {
        return perLimit;
    }

    /**
     * 每人限领张数
     */
    public void setPerLimit(Integer perLimit) {
        this.perLimit = perLimit;
    }

    /**
     * 使用门槛
     */
    public BigDecimal getMinPoint() {
        return minPoint;
    }

    /**
     * 使用门槛
     */
    public void setMinPoint(BigDecimal minPoint) {
        this.minPoint = minPoint;
    }

    /**
     * 开始时间
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * 使用类型[0->全场通用；1->指定分类；2->指定商品]
     */
    public Integer getUseType() {
        return useType;
    }

    /**
     * 使用类型[0->全场通用；1->指定分类；2->指定商品]
     */
    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    /**
     * 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 发行数量
     */
    public Integer getPublishCount() {
        return publishCount;
    }

    /**
     * 发行数量
     */
    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    /**
     * 已使用数量
     */
    public Integer getUseCount() {
        return useCount;
    }

    /**
     * 已使用数量
     */
    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    /**
     * 领取数量
     */
    public Integer getReceiveCount() {
        return receiveCount;
    }

    /**
     * 领取数量
     */
    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }

    /**
     * 可以领取的开始日期
     */
    public LocalDateTime getEnableStartTime() {
        return enableStartTime;
    }

    /**
     * 可以领取的开始日期
     */
    public void setEnableStartTime(LocalDateTime enableStartTime) {
        this.enableStartTime = enableStartTime;
    }

    /**
     * 可以领取的结束日期
     */
    public LocalDateTime getEnableEndTime() {
        return enableEndTime;
    }

    /**
     * 可以领取的结束日期
     */
    public void setEnableEndTime(LocalDateTime enableEndTime) {
        this.enableEndTime = enableEndTime;
    }

    /**
     * 优惠码
     */
    public String getCode() {
        return code;
    }

    /**
     * 优惠码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    public Integer getMemberLevel() {
        return memberLevel;
    }

    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    /**
     * 发布状态[0-未发布，1-已发布]
     */
    public Integer getPublish() {
        return publish;
    }

    /**
     * 发布状态[0-未发布，1-已发布]
     */
    public void setPublish(Integer publish) {
        this.publish = publish;
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
        CouponEntity other = (CouponEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCouponType() == null ? other.getCouponType() == null : this.getCouponType().equals(other.getCouponType()))
                && (this.getCouponImg() == null ? other.getCouponImg() == null : this.getCouponImg().equals(other.getCouponImg()))
                && (this.getCouponName() == null ? other.getCouponName() == null : this.getCouponName().equals(other.getCouponName()))
                && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
                && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                && (this.getPerLimit() == null ? other.getPerLimit() == null : this.getPerLimit().equals(other.getPerLimit()))
                && (this.getMinPoint() == null ? other.getMinPoint() == null : this.getMinPoint().equals(other.getMinPoint()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getUseType() == null ? other.getUseType() == null : this.getUseType().equals(other.getUseType()))
                && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
                && (this.getPublishCount() == null ? other.getPublishCount() == null : this.getPublishCount().equals(other.getPublishCount()))
                && (this.getUseCount() == null ? other.getUseCount() == null : this.getUseCount().equals(other.getUseCount()))
                && (this.getReceiveCount() == null ? other.getReceiveCount() == null : this.getReceiveCount().equals(other.getReceiveCount()))
                && (this.getEnableStartTime() == null ? other.getEnableStartTime() == null : this.getEnableStartTime().equals(other.getEnableStartTime()))
                && (this.getEnableEndTime() == null ? other.getEnableEndTime() == null : this.getEnableEndTime().equals(other.getEnableEndTime()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getMemberLevel() == null ? other.getMemberLevel() == null : this.getMemberLevel().equals(other.getMemberLevel()))
                && (this.getPublish() == null ? other.getPublish() == null : this.getPublish().equals(other.getPublish()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCouponType() == null) ? 0 : getCouponType().hashCode());
        result = prime * result + ((getCouponImg() == null) ? 0 : getCouponImg().hashCode());
        result = prime * result + ((getCouponName() == null) ? 0 : getCouponName().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getPerLimit() == null) ? 0 : getPerLimit().hashCode());
        result = prime * result + ((getMinPoint() == null) ? 0 : getMinPoint().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getUseType() == null) ? 0 : getUseType().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getPublishCount() == null) ? 0 : getPublishCount().hashCode());
        result = prime * result + ((getUseCount() == null) ? 0 : getUseCount().hashCode());
        result = prime * result + ((getReceiveCount() == null) ? 0 : getReceiveCount().hashCode());
        result = prime * result + ((getEnableStartTime() == null) ? 0 : getEnableStartTime().hashCode());
        result = prime * result + ((getEnableEndTime() == null) ? 0 : getEnableEndTime().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getMemberLevel() == null) ? 0 : getMemberLevel().hashCode());
        result = prime * result + ((getPublish() == null) ? 0 : getPublish().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponType=").append(couponType);
        sb.append(", couponImg=").append(couponImg);
        sb.append(", couponName=").append(couponName);
        sb.append(", num=").append(num);
        sb.append(", amount=").append(amount);
        sb.append(", perLimit=").append(perLimit);
        sb.append(", minPoint=").append(minPoint);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", useType=").append(useType);
        sb.append(", note=").append(note);
        sb.append(", publishCount=").append(publishCount);
        sb.append(", useCount=").append(useCount);
        sb.append(", receiveCount=").append(receiveCount);
        sb.append(", enableStartTime=").append(enableStartTime);
        sb.append(", enableEndTime=").append(enableEndTime);
        sb.append(", code=").append(code);
        sb.append(", memberLevel=").append(memberLevel);
        sb.append(", publish=").append(publish);
        sb.append("]");
        return sb.toString();
    }
}