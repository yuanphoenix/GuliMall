package com.atguigu.gulimall.order.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import to.order.LockTo;
import utils.R;

@FeignClient("gulimall-ware")
public interface WareFeign {

  @PostMapping("/ware/waresku/hasstock")
  R getSkuHasStock(@RequestBody List<Long> skuIds);


  @PostMapping("/lock")
   R lock(@RequestBody List<LockTo> lockToList);
}
