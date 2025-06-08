package to;

import java.math.BigDecimal;
import java.util.List;

public class SkuReducitionTo {

  private Long skuId;
  private int fullCount; //满几件
  private BigDecimal discount;//打几折	折扣率（1 为不打折）
  private int countStatus;//	是否启用满减折扣
  private BigDecimal fullPrice;//	满减门槛（元）
  private BigDecimal reducePrice;//满减金额
  private int priceStatus;//是否启用满减
  private List<MemberPrice> memberPrice;//	各等级会员价


  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public int getFullCount() {
    return fullCount;
  }

  public void setFullCount(int fullCount) {
    this.fullCount = fullCount;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public int getCountStatus() {
    return countStatus;
  }

  public void setCountStatus(int countStatus) {
    this.countStatus = countStatus;
  }

  public BigDecimal getFullPrice() {
    return fullPrice;
  }

  public void setFullPrice(BigDecimal fullPrice) {
    this.fullPrice = fullPrice;
  }

  public BigDecimal getReducePrice() {
    return reducePrice;
  }

  public void setReducePrice(BigDecimal reducePrice) {
    this.reducePrice = reducePrice;
  }

  public int getPriceStatus() {
    return priceStatus;
  }

  public void setPriceStatus(int priceStatus) {
    this.priceStatus = priceStatus;
  }

  public List<MemberPrice> getMemberPrice() {
    return memberPrice;
  }

  public void setMemberPrice(List<MemberPrice> memberPrice) {
    this.memberPrice = memberPrice;
  }
}
