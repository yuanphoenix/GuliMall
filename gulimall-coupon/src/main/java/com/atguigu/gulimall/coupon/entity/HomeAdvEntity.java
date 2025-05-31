package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 首页轮播广告
 *
 * @TableName sms_home_adv
 */
@TableName(value = "sms_home_adv")
public class HomeAdvEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 名字
   */
  private String name;

  /**
   * 图片地址
   */
  private String pic;

  /**
   * 开始时间
   */
  private LocalDateTime startTime;

  /**
   * 结束时间
   */
  private LocalDateTime endTime;

  /**
   * 状态
   */
  private Integer status;

  /**
   * 点击数
   */
  private Integer clickCount;

  /**
   * 广告详情连接地址
   */
  private String url;

  /**
   * 备注
   */
  private String note;

  /**
   * 排序
   */
  private Integer sort;

  /**
   * 发布者
   */
  private Long publisherId;

  /**
   * 审核者
   */
  private Long authId;

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
   * 名字
   */
  public String getName() {
    return name;
  }

  /**
   * 名字
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 图片地址
   */
  public String getPic() {
    return pic;
  }

  /**
   * 图片地址
   */
  public void setPic(String pic) {
    this.pic = pic;
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
   * 状态
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 状态
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * 点击数
   */
  public Integer getClickCount() {
    return clickCount;
  }

  /**
   * 点击数
   */
  public void setClickCount(Integer clickCount) {
    this.clickCount = clickCount;
  }

  /**
   * 广告详情连接地址
   */
  public String getUrl() {
    return url;
  }

  /**
   * 广告详情连接地址
   */
  public void setUrl(String url) {
    this.url = url;
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
   * 发布者
   */
  public Long getPublisherId() {
    return publisherId;
  }

  /**
   * 发布者
   */
  public void setPublisherId(Long publisherId) {
    this.publisherId = publisherId;
  }

  /**
   * 审核者
   */
  public Long getAuthId() {
    return authId;
  }

  /**
   * 审核者
   */
  public void setAuthId(Long authId) {
    this.authId = authId;
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
    HomeAdvEntity other = (HomeAdvEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
        : this.getName().equals(other.getName()))
        && (this.getPic() == null ? other.getPic() == null : this.getPic().equals(other.getPic()))
        && (this.getStartTime() == null ? other.getStartTime() == null
        : this.getStartTime().equals(other.getStartTime()))
        && (this.getEndTime() == null ? other.getEndTime() == null
        : this.getEndTime().equals(other.getEndTime()))
        && (this.getStatus() == null ? other.getStatus() == null
        : this.getStatus().equals(other.getStatus()))
        && (this.getClickCount() == null ? other.getClickCount() == null
        : this.getClickCount().equals(other.getClickCount()))
        && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
        && (this.getNote() == null ? other.getNote() == null
        : this.getNote().equals(other.getNote()))
        && (this.getSort() == null ? other.getSort() == null
        : this.getSort().equals(other.getSort()))
        && (this.getPublisherId() == null ? other.getPublisherId() == null
        : this.getPublisherId().equals(other.getPublisherId()))
        && (this.getAuthId() == null ? other.getAuthId() == null
        : this.getAuthId().equals(other.getAuthId()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getPic() == null) ? 0 : getPic().hashCode());
    result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
    result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
    result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
    result = prime * result + ((getClickCount() == null) ? 0 : getClickCount().hashCode());
    result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
    result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
    result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
    result = prime * result + ((getPublisherId() == null) ? 0 : getPublisherId().hashCode());
    result = prime * result + ((getAuthId() == null) ? 0 : getAuthId().hashCode());
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
    sb.append(", pic=").append(pic);
    sb.append(", startTime=").append(startTime);
    sb.append(", endTime=").append(endTime);
    sb.append(", status=").append(status);
    sb.append(", clickCount=").append(clickCount);
    sb.append(", url=").append(url);
    sb.append(", note=").append(note);
    sb.append(", sort=").append(sort);
    sb.append(", publisherId=").append(publisherId);
    sb.append(", authId=").append(authId);
    sb.append("]");
    return sb.toString();
  }
}