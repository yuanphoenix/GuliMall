package com.atguigu.gulimall.gulimallcart.service;

import com.atguigu.gulimall.gulimallcart.vo.Cart;
import to.cart.CartItemTo;
import java.util.List;
import to.MemberEntityVo;

public interface CartService {


   CartItemTo addCart(MemberEntityVo member, Long skuId, Integer num);

   CartItemTo getCartItemBySkuId(MemberEntityVo memberId, Long skuId);

   Cart getCart(MemberEntityVo member);

  Boolean changeCart(CartItemTo cartItem, MemberEntityVo memberEntityVo);

  Boolean deleteByItem(List<CartItemTo> cartItemList, MemberEntityVo memberEntityVo);
}
