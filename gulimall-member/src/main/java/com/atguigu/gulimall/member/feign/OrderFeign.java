package com.atguigu.gulimall.member.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import to.order.OrderInfoTo;
import to.order.OrderPayedEvent;
import utils.R;

@FeignClient("gulimall-order")
public interface OrderFeign {


  @PostMapping("/order/order/changeOrderToPayed")
  R changeOrderToPayed(@RequestBody OrderPayedEvent orderPayedEvent);


  @PostMapping("/order/order/list")
  public R list(@RequestBody OrderInfoTo pageDTO);
}
