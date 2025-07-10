package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.ProductAttrValueEntity;
import com.atguigu.gulimall.product.mapper.ProductAttrValueMapper;
import com.atguigu.gulimall.product.service.ProductAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author tifa
 * @description 针对表【pms_product_attr_value(spu属性值)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class ProductAttrValueServiceImpl extends
    ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity>
    implements ProductAttrValueService {

  @Override
  public List<ProductAttrValueEntity> selectBySpuId(Long spuId) {
    return baseMapper.selectList(
        new LambdaQueryWrapper<ProductAttrValueEntity>().eq(ProductAttrValueEntity::getSpuId,
            spuId));
  }

  @Override
  public boolean updateBySpuInfo(Long spuId,
      List<ProductAttrValueEntity> productAttrValueEntityList) {

    for (ProductAttrValueEntity productAttrValueEntity : productAttrValueEntityList) {
      ProductAttrValueEntity productAttrValueEntityFromDataBase = baseMapper.selectOne(
          new LambdaQueryWrapper<ProductAttrValueEntity>()
              .eq(ProductAttrValueEntity::getSpuId, spuId)
              .eq(ProductAttrValueEntity::getAttrId, productAttrValueEntity.getAttrId()));

      productAttrValueEntityFromDataBase.setAttrValue(productAttrValueEntity.getAttrValue());
      productAttrValueEntityFromDataBase.setAttrId(productAttrValueEntity.getAttrId());
      productAttrValueEntityFromDataBase.setAttrSort(productAttrValueEntity.getAttrSort());
      productAttrValueEntityFromDataBase.setQuickShow(productAttrValueEntity.getQuickShow());
      saveOrUpdate(productAttrValueEntityFromDataBase);
    }

    return true;
  }
}




