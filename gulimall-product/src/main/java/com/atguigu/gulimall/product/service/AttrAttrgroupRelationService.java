package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【pms_attr_attrgroup_relation(属性&属性分组关联)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

  boolean checkAndSave(List<AttrAttrgroupRelationEntity> entity);

  boolean removeRelationList(List<AttrAttrgroupRelationEntity> entityList);
}
