package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.vo.AttrResponseVo;
import com.atguigu.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import utils.PageDTO;

/**
 * @author tifa
 * @description 针对表【pms_attr(商品属性)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface AttrService extends IService<AttrEntity> {

  boolean saveVo(AttrVo attr);

  IPage<AttrResponseVo> getBaseList(Long catalogId, PageDTO pageDTO);

  AttrResponseVo getAttrResponseVo(Long id);

  boolean updateAttrVo(AttrVo attr);

  IPage<AttrResponseVo> getSaleList(Long catalogId, PageDTO pageDTO);

  boolean removeAttrAndRelationByIds(List<Long> ids);
}
