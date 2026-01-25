package to.order;

public class LockSkuTo {


  private Long skuId;
  //  需要锁住的库存
  private Integer stockLocked;


  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Integer getStockLocked() {
    return stockLocked;
  }

  public void setStockLocked(Integer stockLocked) {
    this.stockLocked = stockLocked;
  }
}
