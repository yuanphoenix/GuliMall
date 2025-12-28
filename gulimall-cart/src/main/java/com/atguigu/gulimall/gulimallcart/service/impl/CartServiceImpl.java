package com.atguigu.gulimall.gulimallcart.service.impl;

import com.atguigu.gulimall.gulimallcart.feign.SkuFeignService;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.CartItem;
import com.atguigu.gulimall.gulimallcart.vo.SkuInfoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import to.MemberEntityVo;
import utils.R;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

  private final StringRedisTemplate redisTemplate;
  private final SkuFeignService skuFeignService;
  private final ObjectMapper objectMapper;
  private final Gson gson;

  public CartServiceImpl(StringRedisTemplate redisTemplate, SkuFeignService skuFeignService,
      ObjectMapper objectMapper, Gson gson) {
    this.redisTemplate = redisTemplate;
    this.skuFeignService = skuFeignService;
    this.objectMapper = objectMapper;
    this.gson = gson;
  }


  @Override
  public CartItem addCart(MemberEntityVo member, Long skuId, Integer num) {
    R info = skuFeignService.info(skuId);
    if (info == null) {
      return null;
    }
    var skuEntity = objectMapper.convertValue(info.get("data"), SkuInfoEntity.class);
    Object object = redisTemplate.boundHashOps("cart:" + member.getId())
        .get(skuEntity.getSkuId().toString());
    CartItem cartItem = new CartItem();
    cartItem.setTitle(skuEntity.getSkuTitle());
    cartItem.setSkuId(skuId);
    cartItem.setChecked(true);
    cartItem.setImage(skuEntity.getSkuDefaultImg());
    cartItem.setPrice(skuEntity.getPrice());
    if (object != null) {
//      如果用户已经加过购物车了，那么只要更新数量就可以
      var oldCartItem = gson.fromJson(object.toString(), CartItem.class);
      cartItem.setCount(oldCartItem.getCount() + num);
    } else {
      cartItem.setCount(num);
    }

    redisTemplate.boundHashOps("cart:" + member.getId())
        .put(String.valueOf(skuId), gson.toJson(cartItem));
    return cartItem;
  }

  @Override
  public CartItem getCartItemBySkuId(Long memberId, Long skuId) {
    Object object = redisTemplate.boundHashOps("cart:" + memberId).get(skuId.toString());
    if (object != null) {
      return new Gson().fromJson(object.toString(), CartItem.class);
    }
    return null;
  }

}
