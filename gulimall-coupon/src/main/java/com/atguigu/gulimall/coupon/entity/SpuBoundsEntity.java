package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

/**
 * 商品spu积分设置
 *
 * @TableName sms_spu_bounds
 */
@TableName(value = "sms_spu_bounds")
public class SpuBoundsEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   *
   */
  private Long spuId;

  /**
   * 成长积分
   */
  private BigDecimal growBounds;

  /**
   * 购物积分
   */
  private BigDecimal buyBounds;

  /**
   * 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 -
   * 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
   */
  private Integer work;

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
   *
   */
  public Long getSpuId() {
    return spuId;
  }

  /**
   *
   */
  public void setSpuId(Long spuId) {
    this.spuId = spuId;
  }

  /**
   * 成长积分
   */
  public BigDecimal getGrowBounds() {
    return growBounds;
  }

  /**
   * 成长积分
   */
  public void setGrowBounds(BigDecimal growBounds) {
    this.growBounds = growBounds;
  }

  /**
   * 购物积分
   */
  public BigDecimal getBuyBounds() {
    return buyBounds;
  }

  /**
   * 购物积分
   */
  public void setBuyBounds(BigDecimal buyBounds) {
    this.buyBounds = buyBounds;
  }

  /**
   * 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 -
   * 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
   */
  public Integer getWork() {
    return work;
  }

  /**
   * 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 -
   * 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
   */
  public void setWork(Integer work) {
    this.work = work;
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
    SpuBoundsEntity other = (SpuBoundsEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getSpuId() == null ? other.getSpuId() == null
        : this.getSpuId().equals(other.getSpuId()))
        && (this.getGrowBounds() == null ? other.getGrowBounds() == null
        : this.getGrowBounds().equals(other.getGrowBounds()))
        && (this.getBuyBounds() == null ? other.getBuyBounds() == null
        : this.getBuyBounds().equals(other.getBuyBounds()))
        && (this.getWork() == null ? other.getWork() == null
        : this.getWork().equals(other.getWork()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
    result = prime * result + ((getGrowBounds() == null) ? 0 : getGrowBounds().hashCode());
    result = prime * result + ((getBuyBounds() == null) ? 0 : getBuyBounds().hashCode());
    result = prime * result + ((getWork() == null) ? 0 : getWork().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", spuId=").append(spuId);
    sb.append(", growBounds=").append(growBounds);
    sb.append(", buyBounds=").append(buyBounds);
    sb.append(", work=").append(work);
    sb.append("]");
    return sb.toString();
  }
}