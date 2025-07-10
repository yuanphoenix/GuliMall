package com.atguigu.gulimall.ware.mapper;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tifa
 * @description 针对表【wms_ware_sku(商品库存)】的数据库操作Mapper
 * @createDate 2025-05-08 21:20:50
 * @Entity com.atguigu.gulimall.ware.entity.WareSkuEntity
 */
public interface WareSkuMapper extends BaseMapper<WareSkuEntity> {

  //多个参数一定要为每一个参数生成Param
  void addStock(@Param("wareId") Long wareId, @Param("skuId") Long skuId, @Param("skuNum") Integer skuNum);
}




