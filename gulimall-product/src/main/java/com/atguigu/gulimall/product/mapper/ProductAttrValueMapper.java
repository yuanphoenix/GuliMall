package com.atguigu.gulimall.product.mapper;

import com.atguigu.gulimall.product.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author tifa
 * @description 针对表【pms_product_attr_value(spu属性值)】的数据库操作Mapper
 * @createDate 2025-05-08 20:51:50
 * @Entity com.atguigu.gulimall.product.entity.ProductAttrValueEntity
 */
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValueEntity> {

  /**
   * 根据spuId查询可以被检索到的attr名称和值
   *
   * @param spuId
   * @return
   */
  List<ProductAttrValueEntity> selectSearchValueBySpuId(@Param("spuId") Long spuId);

}




