package com.tifa.gulimallseckill.service;

import java.util.List;
import to.seckill.SeckillSkuRelationEntityTo;

public interface SeckillService {

  /**
   * 将最近3天的活动存入redis
   */
  void uploadSecKillLatest3daySku();

  /**
   * 获取所有正在参加秒杀的商品
   *
   * @return
   */
  List<SeckillSkuRelationEntityTo> getAllSecKillSku();
}
