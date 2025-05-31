package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.mapper.CategoryMapper;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.TreeVoRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    for (var c : list) {
      if (c.getSort() == null) {
        c.setSort(0);
      }
    }
    list.sort(Comparator.comparingInt(CategoryEntity::getSort));
    HashMap<Long, CategoryEntity> categoryMap = new HashMap<>();
    for (CategoryEntity category : list) {
      categoryMap.put(category.getCatId(), category);
    }
    for (CategoryEntity category : list) {
      if (!categoryMap.containsKey(category.getParentCid())) {
        continue;
      }
      categoryMap.get(category.getParentCid()).getChildren().add(category);
      categoryMap.get(category.getParentCid()).setLeaf(Boolean.FALSE);
    }

    return list.stream().filter(e -> e.getCatLevel() == 1).toList();
  }

  @Override
  public boolean checkAndRemove(CategoryEntity category) {
    if (category == null || category.getCatId() == null) {
      return true;
    }

    CategoryEntity byId = this.getById(category.getCatId());
    if (byId == null || byId.getCatId() == null) {
      return true;
    }
    if (!baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", byId.getCatId()))
        .isEmpty()) {
      return false;
    }

    return baseMapper.deleteById(byId.getCatId()) == 1;
  }

  @Override
  public boolean addNew(CategoryEntity category) {
    if (category == null || category.getParentCid() == null) {
      return false;
    }
    CategoryEntity categoryEntity = baseMapper.selectById(category.getParentCid());
    if (categoryEntity == null) {
      return false;
    }
    String categoryName = category.getName();
    BeanUtils.copyProperties(categoryEntity, category);
    category.setCatLevel(categoryEntity.getCatLevel() + 1);
    category.setName(categoryName);
    category.setParentCid(categoryEntity.getCatId());
    category.setCatId(null);
    category.setSort(-1);
    return baseMapper.insert(category) == 1;
  }

  @Override
  public boolean sort(TreeVoRequest treeVoRequest) {
    if (treeVoRequest == null) {
      return false;
    }
    Long target = treeVoRequest.getDropNodeId();
    Long moveId = treeVoRequest.getDraggingNodeId();
    String type = treeVoRequest.getDropType();
    if (target == null || moveId == null || type == null) {
      return false;
    }
    CategoryEntity targetEntity = baseMapper.selectById(target);
    CategoryEntity moveEntity = baseMapper.selectById(moveId);

    if ("inner".equals(type)) {
      moveEntity.setParentCid(targetEntity.getCatId());
      moveEntity.setCatLevel(targetEntity.getCatLevel() + 1);
      moveEntity.setSort(Integer.MIN_VALUE);
      List<CategoryEntity> parentCid = baseMapper.selectList(
          new QueryWrapper<CategoryEntity>().eq("parent_cid", targetEntity.getCatId()));
      parentCid = new ArrayList<>(
          parentCid.stream().filter(a -> a.getCatId().equals(moveEntity.getCatId())).toList());
      parentCid.add(moveEntity);
      parentCid.sort(Comparator.comparingInt(CategoryEntity::getSort));
      for (int i = 0; i < parentCid.size(); i++) {
        parentCid.get(i).setSort(i);
      }
      this.saveOrUpdateBatch(parentCid);
    } else {
      moveEntity.setParentCid(targetEntity.getParentCid());
      moveEntity.setCatLevel(targetEntity.getCatLevel());
      List<CategoryEntity> parentCid = baseMapper.selectList(
          new QueryWrapper<CategoryEntity>().eq("parent_cid", targetEntity.getParentCid()));
      parentCid.sort(Comparator.comparingInt(CategoryEntity::getSort));
      List<CategoryEntity> temp = new ArrayList<>(parentCid.size() + 1);
      for (var entity : parentCid) {
        if (entity.getCatId().equals(moveEntity.getCatId())) {
          continue;
        }
        if (entity.getCatId().equals(target)) {
          if ("before".equals(type)) {
            temp.add(moveEntity);
            temp.add(entity);
            continue;
          } else {
            temp.add(entity);
            temp.add(moveEntity);
            continue;
          }
        }
        temp.add(entity);
      }
      for (int i = 0; i < temp.size(); i++) {
        temp.get(i).setSort(i);
      }
      this.saveOrUpdateBatch(temp);
    }
    updateChildren(moveEntity);
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean removeBatchByEntities(List<CategoryEntity> categoryEntityList) {
    if (categoryEntityList == null) {
      return false;
    }
    return baseMapper.deleteByIds(
        categoryEntityList.stream().map(CategoryEntity::getCatId).filter(Objects::nonNull).toList())
        >= 0;

  }


  private void updateChildren(CategoryEntity category) {
    //更新moveEntity所有子代的catLevel
    LinkedList<CategoryEntity> list = new LinkedList<>();
    list.add(category);
    while (!list.isEmpty()) {
      CategoryEntity categoryEntity = list.removeFirst();
      List<CategoryEntity> parentCid = baseMapper.selectList(
          new QueryWrapper<CategoryEntity>().eq("parent_cid", categoryEntity.getCatId()));
      list.addAll(parentCid);
      for (CategoryEntity parentCategoryEntity : parentCid) {
        parentCategoryEntity.setCatLevel(categoryEntity.getCatLevel() + 1);
      }
      this.saveOrUpdateBatch(parentCid);
    }
  }

}




