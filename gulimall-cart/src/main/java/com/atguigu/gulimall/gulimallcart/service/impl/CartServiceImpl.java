package com.atguigu.gulimall.gulimallcart.service.impl;

import com.atguigu.gulimall.gulimallcart.feign.SkuFeignService;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.SkuInfoEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
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

  public CartServiceImpl(StringRedisTemplate redisTemplate, SkuFeignService skuFeignService,
      ObjectMapper objectMapper) {
    this.redisTemplate = redisTemplate;
    this.skuFeignService = skuFeignService;
    this.objectMapper = objectMapper;
  }


  @Override
  public SkuInfoEntity addCart(MemberEntityVo member, Long skuId, Long num) {
    R info = skuFeignService.info(skuId);
    if (info == null) {
      return null;
    }
    Map<String, String> map = new HashMap<>();

    map.put("skuId", skuId.toString());
    map.put("num", num.toString());
    redisTemplate.opsForHash().putAll("cart:" + member.getId().toString(), map);
    var skuEntity = objectMapper.convertValue(info.get("data"), new TypeReference<SkuInfoEntity>() {
    });
    skuEntity.setNums(num);
    return skuEntity;
  }
}
