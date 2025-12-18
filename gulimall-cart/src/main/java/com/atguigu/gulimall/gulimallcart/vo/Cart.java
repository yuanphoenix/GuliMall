package com.atguigu.gulimall.gulimallcart.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 */
public class Cart {
    @Setter
    @Getter
    private List<CartItem> cartItemList;
    private Integer count; //购物车的商品数量
    private Integer countType; //商品的类型数量
    private BigDecimal totalAmount;
    @Setter
    @Getter
    private BigDecimal reduce = BigDecimal.ZERO; //折扣了多少钱

    public Integer getCount() {
        if (cartItemList != null && !cartItemList.isEmpty()) {
            return cartItemList.stream().mapToInt(CartItem::getCount).sum();
        }
        return 0;
    }

    public Integer getCountType() {
        return this.cartItemList != null ? this.cartItemList.size() : 0;
    }


    public BigDecimal getTotalAmount() {
        if (this.cartItemList == null || this.cartItemList.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return this.cartItemList.stream()
                .filter(CartItem::getChecked)
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}

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

