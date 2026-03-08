package com.tifa.gulimallseckill.service;

import java.util.List;
import to.seckill.SeckillSkuRelationEntityTo;

public interface SeckillService {

  void uploadSecKillLatest3daySku();

  List<SeckillSkuRelationEntityTo> getAllSecKillSku();
}
