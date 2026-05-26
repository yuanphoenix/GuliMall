package com.tifa.gulimallseckill.scheduled;

import com.tifa.gulimallseckill.service.SeckillService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * 秒杀商品定时上架
 * <p>
 * 每天晚上3点；上架最近3天可以秒杀的商品
 *
 */
@Component
public class SeckillSkuSchedule {

  private final SeckillService seckillService;

  public SeckillSkuSchedule(SeckillService seckillService) {
    this.seckillService = seckillService;
  }


  @XxlJob("secondKill3days")
  public void uploadSecKillLatest3daySku() {
    seckillService.uploadSecKillLatest3daySku();
  }

}
