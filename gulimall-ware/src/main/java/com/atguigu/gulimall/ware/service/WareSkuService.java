package com.atguigu.gulimall.ware.service;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.atguigu.gulimall.ware.vo.WarePageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import to.SkuHasStockTo;

/**
 * @author tifa
 * @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service
 * @createDate 2025-05-08 21:20:50
 */
public interface WareSkuService extends IService<WareSkuEntity> {

  IPage<WareSkuEntity> pageWithCondition(WarePageVo pageDTO);


  void addStock(Long wareId, Long skuId, Integer skuNum);

  List<SkuHasStockTo> getSkuHasStock(List<Long> skuIds);
}
