package com.tifa.gulimallseckill.controller;

import com.tifa.gulimallseckill.service.SeckillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import to.seckill.SeckillSkuRelationEntityTo;
import utils.R;

@RestController
public class SecKillController {

  @Autowired
  private SeckillService seckillService;

  @GetMapping("/getCurrentSeckillSkus")
  public R getAllSecKillSku() {
    List<SeckillSkuRelationEntityTo> all = seckillService.getAllSecKillSku();
    return R.ok().put("data", all);
  }
}
