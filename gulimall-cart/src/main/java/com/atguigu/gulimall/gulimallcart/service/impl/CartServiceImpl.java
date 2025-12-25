package com.atguigu.gulimall.gulimallcart.service.impl;

import com.atguigu.gulimall.gulimallcart.feign.SkuFeignService;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.SkuInfoEntity;
import com.fasterxml.jackson.core.type.TypeReference;
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

  public CartServiceImpl(StringRedisTemplate redisTemplate, SkuFeignService skuFeignService) {
    this.redisTemplate = redisTemplate;
    this.skuFeignService = skuFeignService;
  }


  @Override
  public SkuInfoEntity addCart(MemberEntityVo member, Long skuId) {
    R info = skuFeignService.info(skuId);
    if (info == null) {
      return null;
    }
    SkuInfoEntity skuEsModel = info.getData(new TypeReference<SkuInfoEntity>() {
    });

    return skuEsModel;
  }
}
