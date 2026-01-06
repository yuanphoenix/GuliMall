package com.atguigu.gulimall.gulimallcart.service.impl;

import com.atguigu.gulimall.gulimallcart.feign.SkuFeignService;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.Cart;
import com.atguigu.gulimall.gulimallcart.vo.CartItem;
import com.atguigu.gulimall.gulimallcart.vo.SkuInfoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
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
    Object object = this.getBoundGeoOperations(member)
        .get(skuEntity.getSkuId().toString());
    CartItem cartItem = new CartItem();
    cartItem.setTitle(skuEntity.getSkuTitle());
    cartItem.setSkuId(skuId);
    cartItem.setChecked(true);
    cartItem.setImage(skuEntity.getSkuDefaultImg());
    cartItem.setPrice(skuEntity.getPrice());
    if (object != null) {
// 如果用户已经加过购物车了，那么只要更新数量就可以
      var oldCartItem = gson.fromJson(object.toString(), CartItem.class);
      cartItem.setCount(oldCartItem.getCount() + num);
    } else {
      cartItem.setCount(num);
    }
    this.getBoundGeoOperations(member)
        .put(skuId.toString(), gson.toJson(cartItem));
    return cartItem;
  }

  @Override
  public CartItem getCartItemBySkuId(MemberEntityVo member, Long skuId) {
    Object object = this.getBoundGeoOperations(member).get(skuId.toString());
    if (Objects.nonNull(object)) {
      return gson.fromJson(object.toString(), CartItem.class);
    }
    return null;
  }

  @Override
  public Cart getCart(MemberEntityVo member) {
    List<Object> values = this.getBoundGeoOperations(member).values();
    List<CartItem> list = Objects.requireNonNullElse(values, List.of(new Cart())).stream()
        .map(a -> gson.fromJson(a.toString(), CartItem.class)).toList();
    Cart cart = new Cart();
    cart.setCartItemList(list);
    cart.setReduce(BigDecimal.ZERO);
    return cart;
  }

  @Override
  public Boolean changeCart(CartItem cartItem, MemberEntityVo member) {

    if (cartItem == null || cartItem.getSkuId() == null) {
      return false;
    }

    Object object = this.getBoundGeoOperations(member).get(cartItem.getSkuId().toString());
    if (Objects.isNull(object)) {
      return false;
    }
    CartItem originalCartItem = gson.fromJson(object.toString(), CartItem.class);
    originalCartItem.setChecked(Boolean.TRUE.equals(cartItem.getChecked()));
    originalCartItem.setCount(
        cartItem.getCount() == null ? originalCartItem.getCount() : cartItem.getCount());
    this.getBoundGeoOperations(member)
        .put(cartItem.getSkuId().toString(), gson.toJson(originalCartItem));
    return true;
  }

  @Override
  public Boolean deleteByItem(List<CartItem> cartItemList, MemberEntityVo memberEntityVo) {
    this.getBoundGeoOperations(memberEntityVo)
        .delete(cartItemList.stream().map(CartItem::getSkuId).map(String::valueOf).toArray(String[]::new));
    return true;
  }

  private BoundHashOperations<String, Object, Object> getBoundGeoOperations(
      MemberEntityVo member) {
    return redisTemplate.boundHashOps("cart:" + member.getId());
  }

}
