package com.atguigu.gulimall.gulimallcart.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import to.cart.CartItemTo;

/**
 * 购物车
 */
public class Cart {
    @Setter
    @Getter
    private List<CartItemTo> cartItemList;
    private Integer count; //购物车的商品数量
    private Integer countType; //商品的类型数量
    private BigDecimal totalAmount;
    @Setter
    @Getter
    private BigDecimal reduce = BigDecimal.ZERO; //折扣了多少钱

    public Integer getCount() {
        if (cartItemList != null && !cartItemList.isEmpty()) {
            return cartItemList.stream().mapToInt(CartItemTo::getCount).sum();
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
                .filter(CartItemTo::getChecked)
                .map(CartItemTo::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.FLOOR);
    }

    public List<CartItemTo> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemTo> cartItemList) {
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

