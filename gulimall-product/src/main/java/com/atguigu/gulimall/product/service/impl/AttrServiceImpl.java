package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.mapper.AttrAttrgroupRelationMapper;
import com.atguigu.gulimall.product.mapper.AttrGroupMapper;
import com.atguigu.gulimall.product.mapper.CategoryMapper;
import com.atguigu.gulimall.product.service.AttrAttrgroupRelationService;
import com.atguigu.gulimall.product.vo.AttrResponseVo;
import com.atguigu.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.gulimall.product.mapper.AttrMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import utils.PageDTO;
import utils.PageUtils;

import java.util.LinkedList;
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
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

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
        IPage<AttrEntity> page = this.page(PageUtils.of(pageDTO), wrapper);


        return new PageUtils<>(page).convertTo(attr -> {
            AttrResponseVo attrResponseVo = new AttrResponseVo();
            BeanUtils.copyProperties(attr, attrResponseVo);
            Optional.ofNullable(attrAttrgroupRelationService.getOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId())))
                    .ifPresent(one -> {
                        AttrGroupEntity attrGroupEntity = attrGroupMapper.selectById(one.getAttrGroupId());
                        attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());

                        CategoryEntity categoryEntity = categoryMapper.selectById(attr.getCatalogId());
                        attrResponseVo.setCatalogName(categoryEntity.getName());
                    });
            return attrResponseVo;
        });
    }

    @Override
    public AttrResponseVo getAttrResponseVo(Long id) {
        AttrResponseVo attrResponseVo = new AttrResponseVo();
        AttrEntity attrEntity = baseMapper.selectById(id);
        BeanUtils.copyProperties(attrEntity, attrResponseVo);
        Long catalogId = attrEntity.getCatalogId();
        LinkedList<Long> catalogIds = new LinkedList<>();
        while (catalogId != 0) {
            catalogIds.addFirst(catalogId);
            CategoryEntity categoryEntity = categoryMapper.selectById(catalogId);
            attrResponseVo.setCatalogName(ObjectUtils.isEmpty(attrResponseVo.getCatalogName()) ? attrResponseVo.getCatalogName() : categoryEntity.getName());
            catalogId = categoryEntity.getParentCid();
        }
        attrResponseVo.setCatalogPath(catalogIds);
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationMapper.selectOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity::getAttrId, id));
        if (attrAttrgroupRelationEntity != null) {
            attrResponseVo.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
        }


        return attrResponseVo;
    }

    @Transactional
    @Override
    public boolean updateAttrVo(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        baseMapper.updateById(attrEntity);
        AttrAttrgroupRelationEntity attrgroupRelationEntity = attrAttrgroupRelationMapper.selectOne(new LambdaQueryWrapper<AttrAttrgroupRelationEntity>().eq(AttrAttrgroupRelationEntity::getAttrId, attr.getAttrId()));

        attrgroupRelationEntity = attrgroupRelationEntity == null ? new AttrAttrgroupRelationEntity() : attrgroupRelationEntity;

        attrgroupRelationEntity.setAttrId(attr.getAttrId());
        attrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());

        return attrAttrgroupRelationMapper.insertOrUpdate(attrgroupRelationEntity);

    }
}




