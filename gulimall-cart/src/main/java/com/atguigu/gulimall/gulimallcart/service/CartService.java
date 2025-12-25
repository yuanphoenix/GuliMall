package com.atguigu.gulimall.gulimallcart.service;

import com.atguigu.gulimall.gulimallcart.vo.SkuInfoEntity;
import to.MemberEntityVo;

public interface CartService {


   SkuInfoEntity addCart(MemberEntityVo member, Long skuId);
}
