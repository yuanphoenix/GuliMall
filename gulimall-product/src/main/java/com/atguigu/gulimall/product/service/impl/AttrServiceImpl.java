package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.mapper.AttrGroupMapper;
import com.atguigu.gulimall.product.mapper.CategoryMapper;
import com.atguigu.gulimall.product.service.AttrAttrgroupRelationService;
import com.atguigu.gulimall.product.vo.AttrResponseVo;
import com.atguigu.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.gulimall.product.mapper.AttrMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.PageDTO;
import utils.PageUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tifa
 * @description 针对表【pms_attr(商品属性)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, AttrEntity>
        implements AttrService {


    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AttrGroupMapper attrGroupMapper;


    private final AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    public AttrServiceImpl(AttrAttrgroupRelationService attrAttrgroupRelationService) {
        this.attrAttrgroupRelationService = attrAttrgroupRelationService;
    }

    @Transactional
    @Override
    public boolean saveVo(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        baseMapper.insert(attrEntity);
        Long attrId = attrEntity.getAttrId();
        Long attrGroupId = attr.getAttrGroupId();
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(attrId);
//        attrAttrgroupRelationEntity.setAttrSort(attrEntity);
        attrAttrgroupRelationEntity.setAttrGroupId(attrGroupId);
        return attrAttrgroupRelationService.save(attrAttrgroupRelationEntity);

    }

    @Override
    public IPage<AttrResponseVo> getBaseList(Long catalogId, PageDTO pageDTO) {
        var wrapper = new LambdaQueryWrapper<AttrEntity>();
        if (catalogId != 0) {
            wrapper.eq(AttrEntity::getCatalogId, catalogId);
        }
        wrapper.and(o -> o.eq(AttrEntity::getCatalogId, catalogId).or().like(AttrEntity::getAttrName, pageDTO.getKey()));
        IPage<AttrEntity> page = this.page(new PageUtils<AttrEntity>().getPageList(pageDTO), wrapper);
        return new PageUtils<>(page).convertTo(src -> {
            AttrResponseVo attrResponseVo = new AttrResponseVo();
            BeanUtils.copyProperties(src, attrResponseVo);
            Optional.ofNullable(attrAttrgroupRelationService.getOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity::getAttrId, src.getAttrId())))
                    .ifPresent(one -> {
                        AttrGroupEntity attrGroupEntity = attrGroupMapper.selectById(one.getAttrGroupId());
                        attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());

                        CategoryEntity categoryEntity = categoryMapper.selectById(src.getCatalogId());
                        attrResponseVo.setCatalogName(categoryEntity.getName());
                    });
            return attrResponseVo;
        });
    }
}




