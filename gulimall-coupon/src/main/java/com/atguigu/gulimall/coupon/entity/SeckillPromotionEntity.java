package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 秒杀活动
 *
 * @TableName sms_seckill_promotion
 */
@TableName(value = "sms_seckill_promotion")
public class SeckillPromotionEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 活动标题
   */
  private String title;

  /**
   * 开始日期
   */
  private LocalDateTime startTime;

  /**
   * 结束日期
   */
  private LocalDateTime endTime;

  /**
   * 上下线状态
   */
  private Integer status;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * 创建人
   */
  private Long userId;

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
   * 活动标题
   */
  public String getTitle() {
    return title;
  }

  /**
   * 活动标题
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * 开始日期
   */
  public LocalDateTime getStartTime() {
    return startTime;
  }

  /**
   * 开始日期
   */
  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * 结束日期
   */
  public LocalDateTime getEndTime() {
    return endTime;
  }

  /**
   * 结束日期
   */
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  /**
   * 上下线状态
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 上下线状态
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * 创建时间
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * 创建时间
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  /**
   * 创建人
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * 创建人
   */
  public void setUserId(Long userId) {
    this.userId = userId;
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
    SeckillPromotionEntity other = (SeckillPromotionEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getTitle() == null ? other.getTitle() == null
        : this.getTitle().equals(other.getTitle()))
        && (this.getStartTime() == null ? other.getStartTime() == null
        : this.getStartTime().equals(other.getStartTime()))
        && (this.getEndTime() == null ? other.getEndTime() == null
        : this.getEndTime().equals(other.getEndTime()))
        && (this.getStatus() == null ? other.getStatus() == null
        : this.getStatus().equals(other.getStatus()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getUserId() == null ? other.getUserId() == null
        : this.getUserId().equals(other.getUserId()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
    result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
    result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
    result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", title=").append(title);
    sb.append(", startTime=").append(startTime);
    sb.append(", endTime=").append(endTime);
    sb.append(", status=").append(status);
    sb.append(", createTime=").append(createTime);
    sb.append(", userId=").append(userId);
    sb.append("]");
    return sb.toString();
  }
}