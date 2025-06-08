package com.atguigu.gulimall.coupon.service;

import com.atguigu.gulimall.coupon.entity.SkuFullReductionEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import to.SkuReducitionTo;

/**
 * @author tifa
 * @description 针对表【sms_sku_full_reduction(商品满减信息)】的数据库操作Service
 * @createDate 2025-05-08 21:07:54
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

  boolean saveInfoList(List<SkuReducitionTo> skuReducitionToList);
}
