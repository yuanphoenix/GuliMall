package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 秒杀活动场次
 *
 * @TableName sms_seckill_session
 */
@TableName(value = "sms_seckill_session")
public class SeckillSessionEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 场次名称
   */
  private String name;

  /**
   * 每日开始时间
   */
  private LocalDateTime startTime;

  /**
   * 每日结束时间
   */
  private LocalDateTime endTime;

  /**
   * 启用状态
   */
  private Integer status;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

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
   * 场次名称
   */
  public String getName() {
    return name;
  }

  /**
   * 场次名称
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 每日开始时间
   */
  public LocalDateTime getStartTime() {
    return startTime;
  }

  /**
   * 每日开始时间
   */
  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * 每日结束时间
   */
  public LocalDateTime getEndTime() {
    return endTime;
  }

  /**
   * 每日结束时间
   */
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  /**
   * 启用状态
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 启用状态
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
    SeckillSessionEntity other = (SeckillSessionEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
        : this.getName().equals(other.getName()))
        && (this.getStartTime() == null ? other.getStartTime() == null
        : this.getStartTime().equals(other.getStartTime()))
        && (this.getEndTime() == null ? other.getEndTime() == null
        : this.getEndTime().equals(other.getEndTime()))
        && (this.getStatus() == null ? other.getStatus() == null
        : this.getStatus().equals(other.getStatus()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
    result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
    result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append(", startTime=").append(startTime);
    sb.append(", endTime=").append(endTime);
    sb.append(", status=").append(status);
    sb.append(", createTime=").append(createTime);
    sb.append("]");
    return sb.toString();
  }
}