package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.vo.SkuItemVo;
import com.atguigu.gulimall.product.vo.SpuPageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【pms_sku_info(sku信息)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

  IPage<SkuInfoEntity> pageWithCondition(SpuPageVo pageDTO);

  List<SkuInfoEntity> getSkuBySpuId(Long spuId);

  /**
   * 1. 获取sku的基本信息 pms_sku_info
   *
   * 2. 获取sku的图片信息 pms_sku_images
   *
   * 3. 获取spu的销售属性组合
   *
   * 4. 商品介绍，共享SPU的数据
   *
   * 5. 获取spu的规格参数
   *
   * @param skuId
   * @return
   */
  SkuItemVo item(Long skuId);
}
