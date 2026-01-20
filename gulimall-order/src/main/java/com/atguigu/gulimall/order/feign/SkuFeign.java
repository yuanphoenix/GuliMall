package com.atguigu.gulimall.order.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import to.cart.CartItem;

@FeignClient("gulimall-product")
public interface SkuFeign {

  @PostMapping("/product/skuinfo/paylist")
  List<CartItem> skuInfoEntityList(
      @RequestBody List<CartItem> cartItemList);
}
