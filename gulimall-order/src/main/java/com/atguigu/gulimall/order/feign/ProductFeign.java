package com.atguigu.gulimall.order.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import to.cart.CartItemTo;
import utils.R;

@FeignClient("gulimall-product")
public interface ProductFeign {

  @PostMapping("/product/skuinfo/paylist")
  List<CartItemTo> skuInfoNewestEntityList(
      @RequestBody List<CartItemTo> cartItemList);



  @GetMapping("/product/spuinfo/listByIds")
  R getSpuByIds(@RequestBody List<Long> spuIds);

}
