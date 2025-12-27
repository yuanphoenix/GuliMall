package com.atguigu.gulimall.gulimallcart.service;

import com.atguigu.gulimall.gulimallcart.vo.CartItem;
import to.MemberEntityVo;

public interface CartService {


   CartItem addCart(MemberEntityVo member, Long skuId, Integer num);
}
