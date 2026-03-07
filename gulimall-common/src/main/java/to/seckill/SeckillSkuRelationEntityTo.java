package to.seckill;

import java.math.BigDecimal;
import java.util.Objects;
import to.SkuInfoEntityTo;

/**
 * 从coupon微服务里粘过来的
 */
public class SeckillSkuRelationEntityTo {

  /**
   * id
   */
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

  private SkuInfoEntityTo skuInfoEntityTo;

  public SkuInfoEntityTo getSkuInfoEntityTo() {
    return skuInfoEntityTo;
  }

  public void setSkuInfoEntityTo(SkuInfoEntityTo skuInfoEntityTo) {
    this.skuInfoEntityTo = skuInfoEntityTo;
  }

  @Override
  public String toString() {
    return "SeckillSkuRelationEntityTo{" +
        "id=" + id +
        ", promotionId=" + promotionId +
        ", promotionSessionId=" + promotionSessionId +
        ", skuId=" + skuId +
        ", seckillPrice=" + seckillPrice +
        ", seckillCount=" + seckillCount +
        ", seckillLimit=" + seckillLimit +
        ", seckillSort=" + seckillSort +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeckillSkuRelationEntityTo that = (SeckillSkuRelationEntityTo) o;
    return Objects.equals(id, that.id) && Objects.equals(promotionId,
        that.promotionId) && Objects.equals(promotionSessionId, that.promotionSessionId)
        && Objects.equals(skuId, that.skuId) && Objects.equals(seckillPrice,
        that.seckillPrice) && Objects.equals(seckillCount, that.seckillCount)
        && Objects.equals(seckillLimit, that.seckillLimit) && Objects.equals(
        seckillSort, that.seckillSort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, promotionId, promotionSessionId, skuId, seckillPrice, seckillCount,
        seckillLimit, seckillSort);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(Long promotionId) {
    this.promotionId = promotionId;
  }

  public Long getPromotionSessionId() {
    return promotionSessionId;
  }

  public void setPromotionSessionId(Long promotionSessionId) {
    this.promotionSessionId = promotionSessionId;
  }

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public BigDecimal getSeckillPrice() {
    return seckillPrice;
  }

  public void setSeckillPrice(BigDecimal seckillPrice) {
    this.seckillPrice = seckillPrice;
  }

  public Integer getSeckillCount() {
    return seckillCount;
  }

  public void setSeckillCount(Integer seckillCount) {
    this.seckillCount = seckillCount;
  }

  public Integer getSeckillLimit() {
    return seckillLimit;
  }

  public void setSeckillLimit(Integer seckillLimit) {
    this.seckillLimit = seckillLimit;
  }

  public Integer getSeckillSort() {
    return seckillSort;
  }

  public void setSeckillSort(Integer seckillSort) {
    this.seckillSort = seckillSort;
  }
}
