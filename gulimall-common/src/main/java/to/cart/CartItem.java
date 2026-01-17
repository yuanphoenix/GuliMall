package to.cart;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItem {

  private Long skuId;
  private String title;
  private Boolean checked;
  private String image;
  private List<String> skuAttr;
  private BigDecimal price;
  private Integer count; //计数

  private BigDecimal totalPrice;

  public BigDecimal getTotalPrice() {
    if (this.count == null || this.price == null) {
      return null;
    }
    return this.price.multiply(new BigDecimal(this.count));
  }


  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<String> getSkuAttr() {
    return skuAttr;
  }

  public void setSkuAttr(List<String> skuAttr) {
    this.skuAttr = skuAttr;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }


}
