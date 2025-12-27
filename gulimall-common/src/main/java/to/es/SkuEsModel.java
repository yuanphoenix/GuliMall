package to.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 商品在es中存储的内容。
 */
public class SkuEsModel {

  private Long skuId;
  private Long spuId;
  private String skuTitle;
  private BigDecimal skuPrice;
  private String skuImg;

  private Long saleCount;
  private Boolean hasStock;
  private Long hotScore;
  private Long brandId; //品牌ID
  private Long catalogId;
  private String brandName;
  private String brandImg;
  private String catalogName; //分类名字


  private List<Attrs> attrs;


  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Long getSpuId() {
    return spuId;
  }

  public void setSpuId(Long spuId) {
    this.spuId = spuId;
  }

  public String getSkuTitle() {
    return skuTitle;
  }

  public void setSkuTitle(String skuTitle) {
    this.skuTitle = skuTitle;
  }

  public BigDecimal getSkuPrice() {
    return skuPrice;
  }

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  public String getSkuPriceString() {
    return new DecimalFormat("#0.00").format(getSkuPrice());
  }

  public void setSkuPrice(BigDecimal skuPrice) {
    this.skuPrice = skuPrice;
  }

  public String getSkuImg() {
    return skuImg;
  }

  public void setSkuImg(String skuImg) {
    this.skuImg = skuImg;
  }

  public Long getSaleCount() {
    return saleCount;
  }

  public void setSaleCount(Long saleCount) {
    this.saleCount = saleCount;
  }

  public Boolean getHasStock() {
    return hasStock;
  }

  public void setHasStock(Boolean hasStock) {
    this.hasStock = hasStock;
  }

  public Long getHotScore() {
    return hotScore;
  }

  public void setHotScore(Long hotScore) {
    this.hotScore = hotScore;
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public Long getCatalogId() {
    return catalogId;
  }

  public void setCatalogId(Long catalogId) {
    this.catalogId = catalogId;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getBrandImg() {
    return brandImg;
  }

  public void setBrandImg(String brandImg) {
    this.brandImg = brandImg;
  }

  public String getCatalogName() {
    return catalogName;
  }

  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
  }

  public List<Attrs> getAttrs() {
    return attrs;
  }

  public void setAttrs(List<Attrs> attrs) {
    this.attrs = attrs;
  }

  public static class Attrs {

    private Long attrId;
    private String attrName;
    private String attrValue;

    public Long getAttrId() {
      return attrId;
    }

    public void setAttrId(Long attrId) {
      this.attrId = attrId;
    }

    public String getAttrName() {
      return attrName;
    }

    public void setAttrName(String attrName) {
      this.attrName = attrName;
    }

    public String getAttrValue() {
      return attrValue;
    }

    public void setAttrValue(String attrValue) {
      this.attrValue = attrValue;
    }


    @Override
    public String toString() {
      return "Attrs{" +
          "attrId=" + attrId +
          ", attrName='" + attrName + '\'' +
          ", attrValue='" + attrValue + '\'' +
          '}';
    }
  }


  @Override
  public String toString() {
    return "SkuEsModel{" +
        "skuId=" + skuId +
        ", spuId=" + spuId +
        ", skuTitle='" + skuTitle + '\'' +
        ", skuPrice=" + skuPrice +
        ", skuImg='" + skuImg + '\'' +
        ", saleCount=" + saleCount +
        ", hasStock=" + hasStock +
        ", hotScore=" + hotScore +
        ", brandId=" + brandId +
        ", catalogId=" + catalogId +
        ", brandName='" + brandName + '\'' +
        ", brandImg='" + brandImg + '\'' +
        ", catalogName='" + catalogName + '\'' +
        ", attrs=" + attrs +
        '}';
  }
}
