package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

/**
 * 秒杀活动商品关联
 *
 * @TableName sms_seckill_sku_relation
 */
@TableName(value = "sms_seckill_sku_relation")
public class SeckillSkuRelationEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 活动id
   */
  private Long promotionId;

  /**
   * 活动场次id
   */
  private Long promotionSessionId;

  /**
   * 商品id
   */
  private Long skuId;

  /**
   * 秒杀价格
   */
  private BigDecimal seckillPrice;

  /**
   * 秒杀总量
   */
  private Integer seckillCount;

  /**
   * 每人限购数量
   */
  private Integer seckillLimit;

  /**
   * 排序
   */
  private Integer seckillSort;

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
   * 活动id
   */
  public Long getPromotionId() {
    return promotionId;
  }

  /**
   * 活动id
   */
  public void setPromotionId(Long promotionId) {
    this.promotionId = promotionId;
  }

  /**
   * 活动场次id
   */
  public Long getPromotionSessionId() {
    return promotionSessionId;
  }

  /**
   * 活动场次id
   */
  public void setPromotionSessionId(Long promotionSessionId) {
    this.promotionSessionId = promotionSessionId;
  }

  /**
   * 商品id
   */
  public Long getSkuId() {
    return skuId;
  }

  /**
   * 商品id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 秒杀价格
   */
  public BigDecimal getSeckillPrice() {
    return seckillPrice;
  }

  /**
   * 秒杀价格
   */
  public void setSeckillPrice(BigDecimal seckillPrice) {
    this.seckillPrice = seckillPrice;
  }

  /**
   * 秒杀总量
   */
  public Integer getSeckillCount() {
    return seckillCount;
  }

  /**
   * 秒杀总量
   */
  public void setSeckillCount(Integer seckillCount) {
    this.seckillCount = seckillCount;
  }

  /**
   * 每人限购数量
   */
  public Integer getSeckillLimit() {
    return seckillLimit;
  }

  /**
   * 每人限购数量
   */
  public void setSeckillLimit(Integer seckillLimit) {
    this.seckillLimit = seckillLimit;
  }

  /**
   * 排序
   */
  public Integer getSeckillSort() {
    return seckillSort;
  }

  /**
   * 排序
   */
  public void setSeckillSort(Integer seckillSort) {
    this.seckillSort = seckillSort;
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
    SeckillSkuRelationEntity other = (SeckillSkuRelationEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getPromotionId() == null ? other.getPromotionId() == null
        : this.getPromotionId().equals(other.getPromotionId()))
        && (this.getPromotionSessionId() == null ? other.getPromotionSessionId() == null
        : this.getPromotionSessionId().equals(other.getPromotionSessionId()))
        && (this.getSkuId() == null ? other.getSkuId() == null
        : this.getSkuId().equals(other.getSkuId()))
        && (this.getSeckillPrice() == null ? other.getSeckillPrice() == null
        : this.getSeckillPrice().equals(other.getSeckillPrice()))
        && (this.getSeckillCount() == null ? other.getSeckillCount() == null
        : this.getSeckillCount().equals(other.getSeckillCount()))
        && (this.getSeckillLimit() == null ? other.getSeckillLimit() == null
        : this.getSeckillLimit().equals(other.getSeckillLimit()))
        && (this.getSeckillSort() == null ? other.getSeckillSort() == null
        : this.getSeckillSort().equals(other.getSeckillSort()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getPromotionId() == null) ? 0 : getPromotionId().hashCode());
    result = prime * result + ((getPromotionSessionId() == null) ? 0
        : getPromotionSessionId().hashCode());
    result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
    result = prime * result + ((getSeckillPrice() == null) ? 0 : getSeckillPrice().hashCode());
    result = prime * result + ((getSeckillCount() == null) ? 0 : getSeckillCount().hashCode());
    result = prime * result + ((getSeckillLimit() == null) ? 0 : getSeckillLimit().hashCode());
    result = prime * result + ((getSeckillSort() == null) ? 0 : getSeckillSort().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", promotionId=").append(promotionId);
    sb.append(", promotionSessionId=").append(promotionSessionId);
    sb.append(", skuId=").append(skuId);
    sb.append(", seckillPrice=").append(seckillPrice);
    sb.append(", seckillCount=").append(seckillCount);
    sb.append(", seckillLimit=").append(seckillLimit);
    sb.append(", seckillSort=").append(seckillSort);
    sb.append("]");
    return sb.toString();
  }
}