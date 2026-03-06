package com.atguigu.gulimall.coupon.service;

import com.atguigu.gulimall.coupon.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageDTO;

/**
 * @author tifa
 * @description 针对表【sms_seckill_session(秒杀活动场次)】的数据库操作Service
 * @createDate 2025-05-08 21:07:54
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

  IPage<SeckillSessionEntity> list(PageDTO pageDTO);
}
