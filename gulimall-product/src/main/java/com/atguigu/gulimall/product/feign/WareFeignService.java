package com.atguigu.gulimall.product.feign;


import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utils.R;

@FeignClient("gulimall-ware")
public interface WareFeignService {

  @PostMapping("/ware/waresku/hasstock")
  R getSkuHasStock(@RequestBody List<Long> skuIds);
}
