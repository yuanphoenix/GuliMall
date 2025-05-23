package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageDTO;

/**
* @author tifa
* @description 针对表【pms_attr_group(属性分组)】的数据库操作Service
* @createDate 2025-05-08 20:51:50
*/
public interface AttrGroupService extends IService<AttrGroupEntity> {


    IPage<AttrGroupEntity> queryPage(PageDTO attrGroupQueryDTO);

    IPage<AttrGroupEntity> queryPage(PageDTO attrGroupQueryDTO, Long catelogId);
}
