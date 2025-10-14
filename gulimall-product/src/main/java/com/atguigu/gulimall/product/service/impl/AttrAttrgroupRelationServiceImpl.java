package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.mapper.AttrAttrgroupRelationMapper;
import com.atguigu.gulimall.product.mapper.AttrGroupMapper;
import com.atguigu.gulimall.product.mapper.AttrMapper;
import com.atguigu.gulimall.product.service.AttrAttrgroupRelationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author tifa
 * @description 针对表【pms_attr_attrgroup_relation(属性&属性分组关联)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class AttrAttrgroupRelationServiceImpl extends
    ServiceImpl<AttrAttrgroupRelationMapper, AttrAttrgroupRelationEntity>
    implements AttrAttrgroupRelationService {

  private final AttrMapper attrMapper;
  private final AttrGroupMapper groupMapper;

  public AttrAttrgroupRelationServiceImpl(AttrMapper attrMapper, AttrGroupMapper groupMapper) {
    this.attrMapper = attrMapper;
    this.groupMapper = groupMapper;
  }

  @Override
  public boolean checkAndSave(List<AttrAttrgroupRelationEntity> entityList) {
    if (ObjectUtils.isEmpty(entityList)) {
      return false;
    }
    for (var entity : entityList) {
      AttrEntity attrEntity = attrMapper.selectById(entity.getAttrId());
      AttrGroupEntity attrGroupEntity = groupMapper.selectById(entity.getAttrGroupId());
      if (attrEntity == null || attrGroupEntity == null) {
        return false;
      }
      var temp = this.getOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(
              AttrAttrgroupRelationEntity::getAttrId, entity.getAttrId())
          .eq(AttrAttrgroupRelationEntity::getAttrGroupId, entity.getAttrGroupId()));
      if (temp != null) {
        return false;
      }
      save(entity);
    }
    return true;
  }

  @Transactional
  @Override
  public boolean removeRelationList(List<AttrAttrgroupRelationEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return true;
    }
    LambdaQueryWrapper<AttrAttrgroupRelationEntity> wrapper = new LambdaQueryWrapper<>();
    wrapper.and(a -> {
      for (var entity : entityList) {
        a.or(w -> {
          w.eq(AttrAttrgroupRelationEntity::getAttrGroupId, entity.getAttrGroupId())
              .eq(AttrAttrgroupRelationEntity::getAttrId, entity.getAttrId());
        });
      }
    });
    return remove(wrapper);
  }
}




