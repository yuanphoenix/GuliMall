package com.atguigu.gulimall.product.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import to.SkuReducitionTo;
import to.SpuBoundsTo;
import utils.R;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {


  @PostMapping("/coupon/spuBounds/save")
  R saveBounds(@RequestBody SpuBoundsTo spuBoundsTo);


  @PostMapping("/coupon/skuFullReduction/saveInfoList")
  R saveSkuReduction(@RequestBody List<SkuReducitionTo> skuReducitionToList);
}
