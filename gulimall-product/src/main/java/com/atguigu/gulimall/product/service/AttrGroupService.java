package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.vo.AttrGroupResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import utils.PageDTO;

/**
 * @author tifa
 * @description 针对表【pms_attr_group(属性分组)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {


  IPage<AttrGroupEntity> queryPage(PageDTO attrGroupQueryDTO);

  IPage<AttrGroupEntity> queryPage(PageDTO attrGroupQueryDTO, Long catelogId);


  IPage<AttrEntity> getNoAttrRelationByGroupId(Long attrgroupId, PageDTO page);

  List<AttrGroupResponseVo> getCatalogIdWithattr(Long catalogId);
}
