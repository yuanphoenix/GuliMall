package com.atguigu.gulimall.gulimallcart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import utils.R;

@FeignClient("gulimall-product")
public interface SkuFeignService {

  @GetMapping("/product/skuinfo/info/{id}")
  R info(@PathVariable Long id);

}
