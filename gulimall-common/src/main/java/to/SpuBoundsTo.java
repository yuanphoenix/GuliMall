package to;

import java.math.BigDecimal;

public class SpuBoundsTo {


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

  public Long getSpuId() {
    return spuId;
  }

  public void setSpuId(Long spuId) {
    this.spuId = spuId;
  }

  public BigDecimal getGrowBounds() {
    return growBounds;
  }

  public void setGrowBounds(BigDecimal growBounds) {
    this.growBounds = growBounds;
  }

  public BigDecimal getBuyBounds() {
    return buyBounds;
  }

  public void setBuyBounds(BigDecimal buyBounds) {
    this.buyBounds = buyBounds;
  }
}
