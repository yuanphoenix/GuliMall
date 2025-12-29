package com.atguigu.gulimall.gulimallcart.vo;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

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

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}

