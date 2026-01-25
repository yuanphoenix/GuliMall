package com.atguigu.gulimall.order.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import to.cart.CartItemTo;

@FeignClient("gulimall-cart")
public interface CartFeign {

  @GetMapping("/api/getCartItems/{memberId}")
  List<CartItemTo> getCartItems(@PathVariable("memberId") Long memberId);
}

