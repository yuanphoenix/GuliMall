package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.mapper.BrandMapper;
import com.atguigu.gulimall.product.mapper.CategoryBrandRelationMapper;
import com.atguigu.gulimall.product.mapper.CategoryMapper;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author tifa
 * @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class CategoryBrandRelationServiceImpl extends
    ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelationEntity>
    implements CategoryBrandRelationService {

  @Autowired
  BrandMapper brandMapper;
  @Autowired
  CategoryMapper categoryMapper;

  @Override
  public boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
    Long brandId = categoryBrandRelation.getBrandId();
    Long catalogId = categoryBrandRelation.getCatalogId();
    if (brandId == null || catalogId == null) {
      return false;
    }
    var relationList = getBaseMapper().selectList(
        new LambdaQueryWrapper<CategoryBrandRelationEntity>().eq(
                CategoryBrandRelationEntity::getBrandId, brandId)
            .eq(CategoryBrandRelationEntity::getCatalogId, catalogId));
    if (!relationList.isEmpty()) {
      return true;
    }
    BrandEntity brandEntity = brandMapper.selectById(brandId);
    CategoryEntity categoryEntity = categoryMapper.selectById(catalogId);
    categoryBrandRelation.setBrandName(brandEntity.getName());
    categoryBrandRelation.setCatalogName(categoryEntity.getName());
    return save(categoryBrandRelation);
  }

  @Override
  public List<CategoryBrandRelationEntity> listByCategoryEntity(CategoryEntity categoryEntity) {
    if (categoryEntity == null || ObjectUtils.isEmpty(categoryEntity.getCatId())) {
      return List.of();
    }
    return getBaseMapper().selectList(new LambdaQueryWrapper<CategoryBrandRelationEntity>().eq(
        CategoryBrandRelationEntity::getCatalogId, categoryEntity.getCatId()));
  }
}




