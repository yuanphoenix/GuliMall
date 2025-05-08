package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 秒杀商品通知订阅
 * @TableName sms_seckill_sku_notice
 */
@TableName(value ="sms_seckill_sku_notice")
public class SeckillSkuNoticeEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * member_id
     */
    private Long memberId;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 活动场次id
     */
    private Long sessionId;

    /**
     * 订阅时间
     */
    private LocalDateTime subcribeTime;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 通知方式[0-短信，1-邮件]
     */
    private Integer noticeType;

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
     * member_id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * member_id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
     * 活动场次id
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * 活动场次id
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 订阅时间
     */
    public LocalDateTime getSubcribeTime() {
        return subcribeTime;
    }

    /**
     * 订阅时间
     */
    public void setSubcribeTime(LocalDateTime subcribeTime) {
        this.subcribeTime = subcribeTime;
    }

    /**
     * 发送时间
     */
    public LocalDateTime getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间
     */
    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 通知方式[0-短信，1-邮件]
     */
    public Integer getNoticeType() {
        return noticeType;
    }

    /**
     * 通知方式[0-短信，1-邮件]
     */
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
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
        SeckillSkuNoticeEntity other = (SeckillSkuNoticeEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSessionId() == null ? other.getSessionId() == null : this.getSessionId().equals(other.getSessionId()))
            && (this.getSubcribeTime() == null ? other.getSubcribeTime() == null : this.getSubcribeTime().equals(other.getSubcribeTime()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getNoticeType() == null ? other.getNoticeType() == null : this.getNoticeType().equals(other.getNoticeType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSessionId() == null) ? 0 : getSessionId().hashCode());
        result = prime * result + ((getSubcribeTime() == null) ? 0 : getSubcribeTime().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getNoticeType() == null) ? 0 : getNoticeType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", skuId=").append(skuId);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", subcribeTime=").append(subcribeTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", noticeType=").append(noticeType);
        sb.append("]");
        return sb.toString();
    }
}