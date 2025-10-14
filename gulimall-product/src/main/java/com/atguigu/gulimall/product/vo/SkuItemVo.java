package com.atguigu.gulimall.product.vo;

import com.atguigu.gulimall.product.entity.SkuImagesEntity;
import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.entity.SpuInfoDescEntity;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * * 1. 获取sku的基本信息 pms_sku_info * * 2. 获取sku的图片信息 pms_sku_images * * 3. 获取spu的销售属性组合 * * 4.
 * 商品介绍，共享SPU的数据 * * 5. 获取spu的规格参数
 */

@Data
public class SkuItemVo {

  SkuInfoEntity skuInfoEntity;
  List<SkuImagesEntity> imagesEntities;

  Boolean hasStock = Boolean.TRUE;
  //这是一个spu下所有sku共享的一个desp，主要是图片
  SpuInfoDescEntity desp;


  //有多少种销售属性组合
  List<ItemSaleAttrVo> saleAttrVos;


  //基本属性列表
  List<SpuItemBaseAttrVo> groupAttrVos;

  /**
   * 获取spu的所有销售属性
   *
   */
  @Data
  public static class ItemSaleAttrVo {

    private Long attrId;
    private String attrName;
    private List<SaleAttrValueVo> attrValues;
  }

  @Data
  public static class SaleAttrValueVo {
    //这个属性值，在属性值相同的情况下，必须是唯一的。

    /** 属性值内容（如“黑色”、“8GB+256GB”） */
    private String attrValue;

    /** 拥有该属性值的所有 SKU ID 列表 */
    private List<Long> skuIds;
  }


  @Data
  public static class SpuItemBaseAttrVo {

    private String groupName;//例如主体
    private List<SpuBaseAttrVo> spuBaseAttrVoList;  //主体下有多少基本属性对
  }


  @Data
  public static class SpuBaseAttrVo {

    private String attrName;
    private String attrValues;
  }


  @Data
  public static class SpuItemBaseAttrFlatDTO {

    private String groupName;
    private String attrName;
    private String attrValues;
  }
}
