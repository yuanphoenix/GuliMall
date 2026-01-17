package com.atguigu.gulimall.order.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gulimall-product")
public interface SkuFeign {

  @PostMapping("/product/skuinfo/paylist")
   List<to.cart.CartItem> skuInfoEntityList(
      @RequestBody List<to.cart.CartItem> cartItemList);
}
