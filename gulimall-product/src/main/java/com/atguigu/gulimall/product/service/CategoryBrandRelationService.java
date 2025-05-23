package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author tifa
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service
* @createDate 2025-05-08 20:51:50
*/
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    boolean saveDetail(CategoryBrandRelationEntity categoryBrandRelation);
}
