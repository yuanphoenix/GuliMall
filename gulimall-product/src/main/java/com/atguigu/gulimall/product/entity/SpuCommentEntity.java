package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 商品评价
 *
 * @TableName pms_spu_comment
 */
@TableName(value = "pms_spu_comment")
public class SpuCommentEntity {

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
   * spu_id
   */
  private Long spuId;

  /**
   * 商品名字
   */
  private String spuName;

  /**
   * 会员昵称
   */
  private String memberNickName;

  /**
   * 星级
   */
  private Integer star;

  /**
   * 会员ip
   */
  private String memberIp;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * 显示状态[0-不显示，1-显示]
   */
  private Integer showStatus;

  /**
   * 购买时属性组合
   */
  private String spuAttributes;

  /**
   * 点赞数
   */
  private Integer likesCount;

  /**
   * 回复数
   */
  private Integer replyCount;

  /**
   * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
   */
  private String resources;

  /**
   * 内容
   */
  private String content;

  /**
   * 用户头像
   */
  private String memberIcon;

  /**
   * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
   */
  private Integer commentType;

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
   * 商品名字
   */
  public String getSpuName() {
    return spuName;
  }

  /**
   * 商品名字
   */
  public void setSpuName(String spuName) {
    this.spuName = spuName;
  }

  /**
   * 会员昵称
   */
  public String getMemberNickName() {
    return memberNickName;
  }

  /**
   * 会员昵称
   */
  public void setMemberNickName(String memberNickName) {
    this.memberNickName = memberNickName;
  }

  /**
   * 星级
   */
  public Integer getStar() {
    return star;
  }

  /**
   * 星级
   */
  public void setStar(Integer star) {
    this.star = star;
  }

  /**
   * 会员ip
   */
  public String getMemberIp() {
    return memberIp;
  }

  /**
   * 会员ip
   */
  public void setMemberIp(String memberIp) {
    this.memberIp = memberIp;
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
   * 显示状态[0-不显示，1-显示]
   */
  public Integer getShowStatus() {
    return showStatus;
  }

  /**
   * 显示状态[0-不显示，1-显示]
   */
  public void setShowStatus(Integer showStatus) {
    this.showStatus = showStatus;
  }

  /**
   * 购买时属性组合
   */
  public String getSpuAttributes() {
    return spuAttributes;
  }

  /**
   * 购买时属性组合
   */
  public void setSpuAttributes(String spuAttributes) {
    this.spuAttributes = spuAttributes;
  }

  /**
   * 点赞数
   */
  public Integer getLikesCount() {
    return likesCount;
  }

  /**
   * 点赞数
   */
  public void setLikesCount(Integer likesCount) {
    this.likesCount = likesCount;
  }

  /**
   * 回复数
   */
  public Integer getReplyCount() {
    return replyCount;
  }

  /**
   * 回复数
   */
  public void setReplyCount(Integer replyCount) {
    this.replyCount = replyCount;
  }

  /**
   * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
   */
  public String getResources() {
    return resources;
  }

  /**
   * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
   */
  public void setResources(String resources) {
    this.resources = resources;
  }

  /**
   * 内容
   */
  public String getContent() {
    return content;
  }

  /**
   * 内容
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 用户头像
   */
  public String getMemberIcon() {
    return memberIcon;
  }

  /**
   * 用户头像
   */
  public void setMemberIcon(String memberIcon) {
    this.memberIcon = memberIcon;
  }

  /**
   * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
   */
  public Integer getCommentType() {
    return commentType;
  }

  /**
   * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
   */
  public void setCommentType(Integer commentType) {
    this.commentType = commentType;
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
    SpuCommentEntity other = (SpuCommentEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getSkuId() == null ? other.getSkuId() == null
        : this.getSkuId().equals(other.getSkuId()))
        && (this.getSpuId() == null ? other.getSpuId() == null
        : this.getSpuId().equals(other.getSpuId()))
        && (this.getSpuName() == null ? other.getSpuName() == null
        : this.getSpuName().equals(other.getSpuName()))
        && (this.getMemberNickName() == null ? other.getMemberNickName() == null
        : this.getMemberNickName().equals(other.getMemberNickName()))
        && (this.getStar() == null ? other.getStar() == null
        : this.getStar().equals(other.getStar()))
        && (this.getMemberIp() == null ? other.getMemberIp() == null
        : this.getMemberIp().equals(other.getMemberIp()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getShowStatus() == null ? other.getShowStatus() == null
        : this.getShowStatus().equals(other.getShowStatus()))
        && (this.getSpuAttributes() == null ? other.getSpuAttributes() == null
        : this.getSpuAttributes().equals(other.getSpuAttributes()))
        && (this.getLikesCount() == null ? other.getLikesCount() == null
        : this.getLikesCount().equals(other.getLikesCount()))
        && (this.getReplyCount() == null ? other.getReplyCount() == null
        : this.getReplyCount().equals(other.getReplyCount()))
        && (this.getResources() == null ? other.getResources() == null
        : this.getResources().equals(other.getResources()))
        && (this.getContent() == null ? other.getContent() == null
        : this.getContent().equals(other.getContent()))
        && (this.getMemberIcon() == null ? other.getMemberIcon() == null
        : this.getMemberIcon().equals(other.getMemberIcon()))
        && (this.getCommentType() == null ? other.getCommentType() == null
        : this.getCommentType().equals(other.getCommentType()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
    result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
    result = prime * result + ((getSpuName() == null) ? 0 : getSpuName().hashCode());
    result = prime * result + ((getMemberNickName() == null) ? 0 : getMemberNickName().hashCode());
    result = prime * result + ((getStar() == null) ? 0 : getStar().hashCode());
    result = prime * result + ((getMemberIp() == null) ? 0 : getMemberIp().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
    result = prime * result + ((getSpuAttributes() == null) ? 0 : getSpuAttributes().hashCode());
    result = prime * result + ((getLikesCount() == null) ? 0 : getLikesCount().hashCode());
    result = prime * result + ((getReplyCount() == null) ? 0 : getReplyCount().hashCode());
    result = prime * result + ((getResources() == null) ? 0 : getResources().hashCode());
    result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
    result = prime * result + ((getMemberIcon() == null) ? 0 : getMemberIcon().hashCode());
    result = prime * result + ((getCommentType() == null) ? 0 : getCommentType().hashCode());
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
    sb.append(", spuId=").append(spuId);
    sb.append(", spuName=").append(spuName);
    sb.append(", memberNickName=").append(memberNickName);
    sb.append(", star=").append(star);
    sb.append(", memberIp=").append(memberIp);
    sb.append(", createTime=").append(createTime);
    sb.append(", showStatus=").append(showStatus);
    sb.append(", spuAttributes=").append(spuAttributes);
    sb.append(", likesCount=").append(likesCount);
    sb.append(", replyCount=").append(replyCount);
    sb.append(", resources=").append(resources);
    sb.append(", content=").append(content);
    sb.append(", memberIcon=").append(memberIcon);
    sb.append(", commentType=").append(commentType);
    sb.append("]");
    return sb.toString();
  }
}