package com.atguigu.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

/**
 * 会员统计信息
 *
 * @TableName ums_member_statistics_info
 */
@TableName(value = "ums_member_statistics_info")
public class MemberStatisticsInfoEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 累计消费金额
     */
    private BigDecimal consumeAmount;

    /**
     * 累计优惠金额
     */
    private BigDecimal couponAmount;

    /**
     * 订单数量
     */
    private Integer orderCount;

    /**
     * 优惠券数量
     */
    private Integer couponCount;

    /**
     * 评价数
     */
    private Integer commentCount;

    /**
     * 退货数量
     */
    private Integer returnOrderCount;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 关注数量
     */
    private Integer attendCount;

    /**
     * 粉丝数量
     */
    private Integer fansCount;

    /**
     * 收藏的商品数量
     */
    private Integer collectProductCount;

    /**
     * 收藏的专题活动数量
     */
    private Integer collectSubjectCount;

    /**
     * 收藏的评论数量
     */
    private Integer collectCommentCount;

    /**
     * 邀请的朋友数量
     */
    private Integer inviteFriendCount;

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
     * 会员id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * 会员id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * 累计消费金额
     */
    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    /**
     * 累计消费金额
     */
    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    /**
     * 累计优惠金额
     */
    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    /**
     * 累计优惠金额
     */
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    /**
     * 订单数量
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * 订单数量
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 优惠券数量
     */
    public Integer getCouponCount() {
        return couponCount;
    }

    /**
     * 优惠券数量
     */
    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    /**
     * 评价数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 评价数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 退货数量
     */
    public Integer getReturnOrderCount() {
        return returnOrderCount;
    }

    /**
     * 退货数量
     */
    public void setReturnOrderCount(Integer returnOrderCount) {
        this.returnOrderCount = returnOrderCount;
    }

    /**
     * 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * 关注数量
     */
    public Integer getAttendCount() {
        return attendCount;
    }

    /**
     * 关注数量
     */
    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }

    /**
     * 粉丝数量
     */
    public Integer getFansCount() {
        return fansCount;
    }

    /**
     * 粉丝数量
     */
    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    /**
     * 收藏的商品数量
     */
    public Integer getCollectProductCount() {
        return collectProductCount;
    }

    /**
     * 收藏的商品数量
     */
    public void setCollectProductCount(Integer collectProductCount) {
        this.collectProductCount = collectProductCount;
    }

    /**
     * 收藏的专题活动数量
     */
    public Integer getCollectSubjectCount() {
        return collectSubjectCount;
    }

    /**
     * 收藏的专题活动数量
     */
    public void setCollectSubjectCount(Integer collectSubjectCount) {
        this.collectSubjectCount = collectSubjectCount;
    }

    /**
     * 收藏的评论数量
     */
    public Integer getCollectCommentCount() {
        return collectCommentCount;
    }

    /**
     * 收藏的评论数量
     */
    public void setCollectCommentCount(Integer collectCommentCount) {
        this.collectCommentCount = collectCommentCount;
    }

    /**
     * 邀请的朋友数量
     */
    public Integer getInviteFriendCount() {
        return inviteFriendCount;
    }

    /**
     * 邀请的朋友数量
     */
    public void setInviteFriendCount(Integer inviteFriendCount) {
        this.inviteFriendCount = inviteFriendCount;
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
        MemberStatisticsInfoEntity other = (MemberStatisticsInfoEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getConsumeAmount() == null ? other.getConsumeAmount() == null : this.getConsumeAmount().equals(other.getConsumeAmount()))
                && (this.getCouponAmount() == null ? other.getCouponAmount() == null : this.getCouponAmount().equals(other.getCouponAmount()))
                && (this.getOrderCount() == null ? other.getOrderCount() == null : this.getOrderCount().equals(other.getOrderCount()))
                && (this.getCouponCount() == null ? other.getCouponCount() == null : this.getCouponCount().equals(other.getCouponCount()))
                && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
                && (this.getReturnOrderCount() == null ? other.getReturnOrderCount() == null : this.getReturnOrderCount().equals(other.getReturnOrderCount()))
                && (this.getLoginCount() == null ? other.getLoginCount() == null : this.getLoginCount().equals(other.getLoginCount()))
                && (this.getAttendCount() == null ? other.getAttendCount() == null : this.getAttendCount().equals(other.getAttendCount()))
                && (this.getFansCount() == null ? other.getFansCount() == null : this.getFansCount().equals(other.getFansCount()))
                && (this.getCollectProductCount() == null ? other.getCollectProductCount() == null : this.getCollectProductCount().equals(other.getCollectProductCount()))
                && (this.getCollectSubjectCount() == null ? other.getCollectSubjectCount() == null : this.getCollectSubjectCount().equals(other.getCollectSubjectCount()))
                && (this.getCollectCommentCount() == null ? other.getCollectCommentCount() == null : this.getCollectCommentCount().equals(other.getCollectCommentCount()))
                && (this.getInviteFriendCount() == null ? other.getInviteFriendCount() == null : this.getInviteFriendCount().equals(other.getInviteFriendCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getConsumeAmount() == null) ? 0 : getConsumeAmount().hashCode());
        result = prime * result + ((getCouponAmount() == null) ? 0 : getCouponAmount().hashCode());
        result = prime * result + ((getOrderCount() == null) ? 0 : getOrderCount().hashCode());
        result = prime * result + ((getCouponCount() == null) ? 0 : getCouponCount().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getReturnOrderCount() == null) ? 0 : getReturnOrderCount().hashCode());
        result = prime * result + ((getLoginCount() == null) ? 0 : getLoginCount().hashCode());
        result = prime * result + ((getAttendCount() == null) ? 0 : getAttendCount().hashCode());
        result = prime * result + ((getFansCount() == null) ? 0 : getFansCount().hashCode());
        result = prime * result + ((getCollectProductCount() == null) ? 0 : getCollectProductCount().hashCode());
        result = prime * result + ((getCollectSubjectCount() == null) ? 0 : getCollectSubjectCount().hashCode());
        result = prime * result + ((getCollectCommentCount() == null) ? 0 : getCollectCommentCount().hashCode());
        result = prime * result + ((getInviteFriendCount() == null) ? 0 : getInviteFriendCount().hashCode());
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
        sb.append(", consumeAmount=").append(consumeAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", couponCount=").append(couponCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", returnOrderCount=").append(returnOrderCount);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", attendCount=").append(attendCount);
        sb.append(", fansCount=").append(fansCount);
        sb.append(", collectProductCount=").append(collectProductCount);
        sb.append(", collectSubjectCount=").append(collectSubjectCount);
        sb.append(", collectCommentCount=").append(collectCommentCount);
        sb.append(", inviteFriendCount=").append(inviteFriendCount);
        sb.append("]");
        return sb.toString();
    }
}