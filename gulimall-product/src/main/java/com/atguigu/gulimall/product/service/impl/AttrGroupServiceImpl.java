package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.mapper.AttrAttrgroupRelationMapper;
import com.atguigu.gulimall.product.mapper.AttrGroupMapper;
import com.atguigu.gulimall.product.mapper.AttrMapper;
import com.atguigu.gulimall.product.service.AttrGroupService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import utils.PageDTO;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【pms_attr_group(属性分组)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity>
    implements AttrGroupService {

  @Autowired
  private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;
  @Autowired
  private AttrMapper attrMapper;

  @Override
  public IPage<AttrGroupEntity> queryPage(PageDTO attrGroupQueryDTO) {
    return this.page(PageUtils.of(attrGroupQueryDTO));
  }

  @Override
  public IPage<AttrGroupEntity> queryPage(PageDTO attrGroupQueryDTO, Long catalogId) {
    var wrapper = new LambdaQueryWrapper<AttrGroupEntity>();
    if (catalogId != 0) {
      wrapper.eq(AttrGroupEntity::getCatalogId, catalogId);
    }
    wrapper.and(o -> o.eq(AttrGroupEntity::getAttrGroupId, catalogId)
        .or()
        .like(AttrGroupEntity::getAttrGroupName, attrGroupQueryDTO.getKey()));
    return this.page(PageUtils.of(attrGroupQueryDTO), wrapper);

  }

  @Override
  public List<AttrEntity> listAttrRelationByGroupId(Long attrgroupId) {
    List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities = attrAttrgroupRelationMapper.selectList(
        new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(
            AttrAttrgroupRelationEntity::getAttrGroupId, attrgroupId));
    List<Long> list = attrAttrgroupRelationEntities.stream()
        .map(AttrAttrgroupRelationEntity::getAttrId).distinct().toList();
    if (CollectionUtils.isEmpty(list)) {
      return List.of();
    }

    return attrMapper.selectList(
        new LambdaQueryWrapper<AttrEntity>().in(AttrEntity::getAttrId, list));
  }

  @Override
  public IPage<AttrEntity> getNoAttrRelationByGroupId(Long attrgroupId, PageDTO page) {
    AttrGroupEntity byId = this.getById(attrgroupId);
    Long catalogId = byId.getCatalogId();

    LambdaQueryWrapper<AttrEntity> wrapper = new LambdaQueryWrapper<>();

    LambdaQueryWrapper<AttrAttrgroupRelationEntity> relationWrapper = new LambdaQueryWrapper<>();
    relationWrapper.select(AttrAttrgroupRelationEntity::getAttrId);
    List<Long> attrIds = attrAttrgroupRelationMapper.selectList(relationWrapper).stream()
        .map(AttrAttrgroupRelationEntity::getAttrId).distinct().toList();

    wrapper.eq(AttrEntity::getCatalogId, catalogId).eq(AttrEntity::getAttrType, 1);
    Optional.of(attrIds).filter(list -> !list.isEmpty())
        .ifPresent(list -> wrapper.notIn(AttrEntity::getAttrId, list));

    return attrMapper.selectPage(PageUtils.of(page), wrapper);
  }

}




