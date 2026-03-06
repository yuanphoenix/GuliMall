package com.atguigu.gulimall.coupon.service.impl;

import com.atguigu.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.atguigu.gulimall.coupon.mapper.SeckillSkuRelationMapper;
import com.atguigu.gulimall.coupon.service.SeckillSkuRelationService;
import com.atguigu.gulimall.coupon.vo.SessionRealtionDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Objects;
import org.springframework.stereotype.Service;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【sms_seckill_sku_relation(秒杀活动商品关联)】的数据库操作Service实现
 * @createDate 2025-05-08 21:07:54
 */
@Service
public class SeckillSkuRelationServiceImpl extends
    ServiceImpl<SeckillSkuRelationMapper, SeckillSkuRelationEntity>
    implements SeckillSkuRelationService {


  @Override
  public IPage<SeckillSkuRelationEntity> pageWithCondition(SessionRealtionDTO pageDTO) {
    return this.baseMapper.selectPage(PageUtils.of(pageDTO),
        new LambdaQueryWrapper<SeckillSkuRelationEntity>().eq(
            Objects.nonNull(pageDTO.getPromotionSessionId()),
            SeckillSkuRelationEntity::getPromotionSessionId, pageDTO.getPromotionSessionId()));
  }
}




