package com.atguigu.gulimall.product.mapper;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.vo.SkuItemVo.SpuItemBaseAttrTo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * @author tifa
 * @description 针对表【pms_sku_info(sku信息)】的数据库操作Mapper
 * @createDate 2025-05-08 20:51:50
 * @Entity com.atguigu.gulimall.product.entity.SkuInfoEntity
 */
public interface SkuInfoMapper extends BaseMapper<SkuInfoEntity> {

  @Select("SELECT pag.attr_group_name as groupName ,ppav.attr_name as attrName,ppav.attr_value as attrValues FROM pms_product_attr_value ppav INNER JOIN pms_attr ON pms_attr.attr_id = ppav.attr_id INNER JOIN pms_attr_attrgroup_relation paar ON paar.attr_id = ppav.attr_id LEFT JOIN pms_attr_group pag ON paar.attr_group_id = pag.attr_group_id WHERE spu_id = #{spuId};")
  List<SpuItemBaseAttrTo> getspuItemBaseAttr(Long spuId);

}




