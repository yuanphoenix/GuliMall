package com.tifa.gulimallseckill.feign;

import com.tifa.gulimallseckill.entity.SeckillSessionEntityTo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("gulimall-coupon")
public interface CouponFeign {

  @GetMapping("/coupon/seckillsession/list3daysSecKill")
  List<SeckillSessionEntityTo> getSecKill3daysLatest();
}
