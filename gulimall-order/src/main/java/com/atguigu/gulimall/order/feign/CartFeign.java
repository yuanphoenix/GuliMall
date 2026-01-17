package com.atguigu.gulimall.order.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import to.cart.CartItem;

@FeignClient("gulimall-cart")
public interface CartFeign {

  @GetMapping("/api/getCartItems/{memberId}")
  List<CartItem> getCartItems(@PathVariable("memberId") Long memberId);
}

