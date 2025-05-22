package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.dto.AttrGroupQueryDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;
import com.atguigu.gulimall.product.mapper.AttrGroupMapper;
import org.springframework.stereotype.Service;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【pms_attr_group(属性分组)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity>
        implements AttrGroupService {

    @Override
    public IPage<AttrGroupEntity> queryPage(AttrGroupQueryDTO attrGroupQueryDTO) {
        return this.page(new PageUtils<AttrGroupEntity>().getPageList(attrGroupQueryDTO));
    }

    @Override
    public IPage<AttrGroupEntity> queryPage(AttrGroupQueryDTO attrGroupQueryDTO, Long catelogId) {
        var wrapper = new LambdaQueryWrapper<AttrGroupEntity>().eq(AttrGroupEntity::getCatelogId, catelogId).and(o -> o.eq(AttrGroupEntity::getAttrGroupId, catelogId).or().like(AttrGroupEntity::getAttrGroupName, attrGroupQueryDTO.getKey()));

        return this.page(new PageUtils<AttrGroupEntity>().getPageList(attrGroupQueryDTO), wrapper);

    }
}




