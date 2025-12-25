package com.atguigu.gulimall.gulimallcart.vo;


import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
class CartItem {
    @Getter
    private Long skuId;
    @Getter
    private String title;
    @Getter
    private Boolean checked;
    @Getter
    private String image;
    @Getter
    private List<String> skuAttr;
    @Getter
    private BigDecimal price;
    @Getter
    private Integer count; //计数
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal(this.count));
    }

}
