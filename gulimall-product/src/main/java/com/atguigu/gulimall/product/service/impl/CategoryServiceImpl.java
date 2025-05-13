package com.atguigu.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
        implements CategoryService {

    @Override
    public List<CategoryEntity> listWithTree() {

        List<CategoryEntity> list = list();
        HashMap<Long, CategoryEntity> categoryMap = new HashMap<>();
        for (CategoryEntity category : list) {
            categoryMap.put(category.getCatId(), category);
        }
        for (CategoryEntity category : list) {
            if (!categoryMap.containsKey(category.getParentCid())) {
                continue;
            }
            categoryMap.get(category.getParentCid()).getChildren().add(category);
        }
        return list.stream().filter(e -> e.getCatLevel() == 1).toList();
    }
}




