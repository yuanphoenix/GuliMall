package to;

import java.util.Objects;

public class SkuHasStockTo {

  private Long skuId;
  private boolean hasStock;
  private Long skuNums;

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Boolean getHasStock() {
    return hasStock;
  }


  public void setHasStock(boolean hasStock) {
    this.hasStock = hasStock;
  }

  public Long getSkuNums() {
    return skuNums;
  }

  public void setSkuNums(Long skuNums) {
    this.skuNums = skuNums;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkuHasStockTo that = (SkuHasStockTo) o;
    return hasStock == that.hasStock && Objects.equals(skuId, that.skuId)
        && Objects.equals(skuNums, that.skuNums);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skuId, hasStock, skuNums);
  }

  @Override
  public String toString() {
    return "SkuHasStockTo{" +
        "skuId=" + skuId +
        ", hasStock=" + hasStock +
        ", skuNums=" + skuNums +
        '}';
  }
}
