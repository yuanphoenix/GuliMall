package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

  boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

  List<CategoryBrandRelationEntity> listByCategoryEntity(CategoryEntity categoryEntity);
}
