package com.atguigu.gulimall.gulimallcart.service;

import com.atguigu.gulimall.gulimallcart.vo.Cart;
import com.atguigu.gulimall.gulimallcart.vo.CartItem;
import java.util.List;
import to.MemberEntityVo;

public interface CartService {


   CartItem addCart(MemberEntityVo member, Long skuId, Integer num);

   CartItem getCartItemBySkuId(MemberEntityVo memberId, Long skuId);

   Cart getCart(MemberEntityVo member);

  Boolean changeCart(CartItem cartItem, MemberEntityVo memberEntityVo);

  Boolean deleteByItem(List<CartItem> cartItemList, MemberEntityVo memberEntityVo);
}
