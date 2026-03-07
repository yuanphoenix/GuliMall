package com.atguigu.gulimall.gulimallcart.service.impl;

import com.atguigu.gulimall.gulimallcart.feign.SkuFeignService;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.Cart;
import com.atguigu.gulimall.gulimallcart.vo.SkuInfoEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import to.MemberEntityVo;
import to.cart.CartItemTo;
import utils.R;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

  private final RedisTemplate redisTemplate;
  private final SkuFeignService skuFeignService;
  @Autowired
  private ObjectMapper objectMapper;

  public CartServiceImpl(RedisTemplate redisTemplate, SkuFeignService skuFeignService) {
    this.redisTemplate = redisTemplate;
    this.skuFeignService = skuFeignService;
  }


  @Override
  public CartItemTo addCart(MemberEntityVo member, Long skuId, Integer num) {
    R info = skuFeignService.info(skuId);
    if (info == null) {
      return null;
    }
    SkuInfoEntity skuEntity = info.getData(new TypeReference<>() {
    });
    Object oldCartItem = this.getBoundGeoOperations(member)
        .get(skuEntity.getSkuId().toString());
    CartItemTo cartItem = new CartItemTo();
    cartItem.setTitle(skuEntity.getSkuTitle());
    cartItem.setSkuId(skuId);
    cartItem.setChecked(true);
    cartItem.setSpuId(skuEntity.getSpuId());
    cartItem.setImage(skuEntity.getSkuDefaultImg());
    cartItem.setPrice(skuEntity.getPrice());
    if (oldCartItem != null) {
// 如果用户已经加过购物车了，那么只要更新数量就可以
      cartItem.setCount(objectMapper.convertValue(oldCartItem, CartItemTo.class).getCount() + num);
    } else {
      cartItem.setCount(num);
    }
    this.getBoundGeoOperations(member)
        .put(skuId.toString(), cartItem);
    return cartItem;
  }

  @Override
  public CartItemTo getCartItemBySkuId(MemberEntityVo member, Long skuId) {
    Object object = this.getBoundGeoOperations(member).get(skuId.toString());
    return objectMapper.convertValue(object, CartItemTo.class);
  }

  @Override
  public Cart getCart(MemberEntityVo member) {
    Cart cart = new Cart();
    cart.setCartItemList(List.of());

    List<Object> list = this.getBoundGeoOperations(member).values();
    if (list == null || list.isEmpty()) {
      return cart;
    }
    List<CartItemTo> list1 = list.stream().map(a -> objectMapper.convertValue(a, CartItemTo.class))
        .toList();

    cart.setCartItemList(list1);
    cart.setReduce(BigDecimal.ZERO);
    return cart;
  }

  @Override
  public Boolean changeCart(CartItemTo cartItem, MemberEntityVo member) {
    if (cartItem == null || cartItem.getSkuId() == null) {
      return false;
    }
    //从redis中取得的
    Object c1 = this.getBoundGeoOperations(member).get(cartItem.getSkuId());
    var originalCartItem = objectMapper.convertValue(c1, CartItemTo.class);
    if (Objects.isNull(originalCartItem)) {
      return false;
    }

    originalCartItem.setChecked(cartItem.getChecked() == null ? originalCartItem.getChecked()
        : Boolean.TRUE.equals(cartItem.getChecked()));

    originalCartItem.setCount(
        cartItem.getCount() == null ? originalCartItem.getCount() : cartItem.getCount());
    this.getBoundGeoOperations(member)
        .put(cartItem.getSkuId().toString(), originalCartItem);
    return true;
  }

  @Override
  public Boolean deleteByItem(List<CartItemTo> cartItemList, MemberEntityVo memberEntityVo) {
    this.getBoundGeoOperations(memberEntityVo)
        .delete(cartItemList.stream().map(CartItemTo::getSkuId).map(String::valueOf)
            .toArray(String[]::new));
    return true;
  }

  private BoundHashOperations<String, String, Object> getBoundGeoOperations(
      MemberEntityVo member) {
    return redisTemplate.boundHashOps("cart:" + member.getId());
  }

}
