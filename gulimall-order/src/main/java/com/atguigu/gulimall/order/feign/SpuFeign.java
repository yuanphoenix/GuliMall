package com.atguigu.gulimall.order.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utils.R;

@FeignClient("gulimall-product")
public interface SpuFeign {

  @GetMapping("/product/spuinfo/listByIds")
  R getSpuByIds(@RequestBody List<Long> spuIds);

}
