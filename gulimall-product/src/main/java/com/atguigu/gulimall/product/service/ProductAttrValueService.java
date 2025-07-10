package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【pms_product_attr_value(spu属性值)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

  List<ProductAttrValueEntity> selectBySpuId(Long spuId);

  boolean updateBySpuInfo(Long spuId, List<ProductAttrValueEntity> productAttrValueEntityList);
}
