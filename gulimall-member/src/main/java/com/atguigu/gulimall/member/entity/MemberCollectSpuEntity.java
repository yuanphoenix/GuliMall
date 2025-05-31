package com.atguigu.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 会员收藏的商品
 *
 * @TableName ums_member_collect_spu
 */
@TableName(value = "ums_member_collect_spu")
public class MemberCollectSpuEntity {

  /**
   * id
   */
  @TableId
  private Long id;

  /**
   * 会员id
   */
  private Long memberId;

  /**
   * spu_id
   */
  private Long spuId;

  /**
   * spu_name
   */
  private String spuName;

  /**
   * spu_img
   */
  private String spuImg;

  /**
   * create_time
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

  /**
   * spu_img
   */
  public String getSpuImg() {
    return spuImg;
  }

  /**
   * spu_img
   */
  public void setSpuImg(String spuImg) {
    this.spuImg = spuImg;
  }

  /**
   * create_time
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * create_time
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
    MemberCollectSpuEntity other = (MemberCollectSpuEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getMemberId() == null ? other.getMemberId() == null
        : this.getMemberId().equals(other.getMemberId()))
        && (this.getSpuId() == null ? other.getSpuId() == null
        : this.getSpuId().equals(other.getSpuId()))
        && (this.getSpuName() == null ? other.getSpuName() == null
        : this.getSpuName().equals(other.getSpuName()))
        && (this.getSpuImg() == null ? other.getSpuImg() == null
        : this.getSpuImg().equals(other.getSpuImg()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
    result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
    result = prime * result + ((getSpuName() == null) ? 0 : getSpuName().hashCode());
    result = prime * result + ((getSpuImg() == null) ? 0 : getSpuImg().hashCode());
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
    sb.append(", memberId=").append(memberId);
    sb.append(", spuId=").append(spuId);
    sb.append(", spuName=").append(spuName);
    sb.append(", spuImg=").append(spuImg);
    sb.append(", createTime=").append(createTime);
    sb.append("]");
    return sb.toString();
  }
}