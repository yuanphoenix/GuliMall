package com.atguigu.gulimall.product.vo;

import com.atguigu.gulimall.product.entity.SkuImagesEntity;
import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.entity.SpuInfoDescEntity;
import java.util.List;

/**
 * * 1. 获取sku的基本信息 pms_sku_info * * 2. 获取sku的图片信息 pms_sku_images * * 3. 获取spu的销售属性组合 * * 4.
 * 商品介绍，共享SPU的数据 * * 5. 获取spu的规格参数
 */
public class SkuItemVo {

  SkuInfoEntity skuInfoEntity;
  List<SkuImagesEntity> imagesEntities;

  SpuInfoDescEntity desp;

  List<SkuItemSaleAttrVo> saleAttrVos;

  public static class SkuItemSaleAttrVo {

    private Long attrId;
    private String attrName;
    private List<String> attrValues;

  }


  public static class  SpuItemBaseAttrVo{

    private String groupName;
  }
}
