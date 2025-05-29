package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.mapper.AttrAttrgroupRelationMapper;
import com.atguigu.gulimall.product.mapper.AttrGroupMapper;
import com.atguigu.gulimall.product.mapper.AttrMapper;
import com.atguigu.gulimall.product.mapper.CategoryMapper;
import com.atguigu.gulimall.product.service.AttrGroupService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.PageDTO;
import utils.PageUtils;

import java.util.List;

/**
 * @author tifa
 * @description 针对表【pms_attr_group(属性分组)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity>
        implements AttrGroupService {
    @Autowired
    private CategoryMapper categoryMapper;
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
        List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities = attrAttrgroupRelationMapper.selectList(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity::getAttrGroupId, attrgroupId));
        List<Long> list = attrAttrgroupRelationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).distinct().toList();
        return attrMapper.selectList(new LambdaQueryWrapper<AttrEntity>().in(AttrEntity::getAttrId, list));
    }

    @Override
    public IPage<AttrEntity> getNoAttrRelationByGroupId(Long attrgroupId, PageDTO page) {
        //TODO 这个没写出来
        AttrGroupEntity byId = this.getById(attrgroupId);
        Long catalogId = byId.getCatalogId();
        List<AttrEntity> attrEntities = attrMapper.selectList(new LambdaQueryWrapper<AttrEntity>().eq(AttrEntity::getCatalogId, catalogId));
        List<Long> list = attrEntities.stream().map(AttrEntity::getAttrId).toList();
        new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity::getAttrGroupId, attrgroupId).notIn(AttrAttrgroupRelationEntity::getAttrId, list);
        return null;
    }
}




