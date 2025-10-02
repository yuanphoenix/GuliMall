package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import com.atguigu.gulimall.product.vo.TreeVoRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @author tifa
 * @description 针对表【pms_category(商品三级分类)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface CategoryService extends IService<CategoryEntity> {

  List<CategoryEntity> listWithTree();

  boolean checkAndRemove(CategoryEntity category);

  boolean addNew(CategoryEntity category);

  boolean sort(TreeVoRequest treeVoRequest);

  boolean removeBatchByEntities(List<CategoryEntity> categoryEntityList);

  List<CategoryEntity> selectLevelOneCategorys();

  Map<String,  List<Catelog2Vo>> getCatalogJson();
}
