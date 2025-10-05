package com.atguigu.gulimall.ware.service.impl;

import com.atguigu.gulimall.ware.entity.PurchaseDetailEntity;
import com.atguigu.gulimall.ware.mapper.PurchaseDetailMapper;
import com.atguigu.gulimall.ware.service.PurchaseDetailService;
import com.atguigu.gulimall.ware.vo.WarePageVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【wms_purchase_detail(采购需求)】的数据库操作Service实现
 * @createDate 2025-05-08 21:20:50
 */
@Service
public class PurchaseDetailServiceImpl extends
    ServiceImpl<PurchaseDetailMapper, PurchaseDetailEntity>
    implements PurchaseDetailService {

  @Override
  public IPage<PurchaseDetailEntity> pageWithCondition(WarePageVo warePageVo) {


    return page(PageUtils.of(warePageVo), new LambdaQueryWrapper<PurchaseDetailEntity>()
        .eq(!ObjectUtils.isEmpty(warePageVo.getWareId()), PurchaseDetailEntity::getWareId,
            warePageVo.getWareId())
        .eq(!ObjectUtils.isEmpty(warePageVo.getStatus()), PurchaseDetailEntity::getStatus,
            warePageVo.getStatus())
        .and(NumberUtils.isCreatable(warePageVo.getKey()), w -> {
          w.eq(PurchaseDetailEntity::getPurchaseId, NumberUtils.toLong(warePageVo.getKey()))
              .or()
              .eq(PurchaseDetailEntity::getSkuId, NumberUtils.toLong(warePageVo.getKey()));
        })

    );
  }
}




