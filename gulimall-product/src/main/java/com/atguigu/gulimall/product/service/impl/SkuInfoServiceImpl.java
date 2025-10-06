package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.mapper.SkuInfoMapper;
import com.atguigu.gulimall.product.service.SkuInfoService;
import com.atguigu.gulimall.product.vo.SkuItemVo;
import com.atguigu.gulimall.product.vo.SpuPageVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【pms_sku_info(sku信息)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfoEntity>
    implements SkuInfoService {


  @Override
  public IPage<SkuInfoEntity> pageWithCondition(SpuPageVo pageDTO) {
    if (pageDTO.getMin() == null) {
      pageDTO.setMin(Long.MIN_VALUE);
    }
    if (pageDTO.getMax() == null || pageDTO.getMax().equals(0L)) {
      pageDTO.setMax(Long.MAX_VALUE);
    }
    if (pageDTO.getMax() < pageDTO.getMin()) {
      Long min = pageDTO.getMin();
      pageDTO.setMin(pageDTO.getMax());
      pageDTO.setMax(min);
    }

    return baseMapper.selectPage(PageUtils.of(pageDTO),
        new LambdaQueryWrapper<SkuInfoEntity>()
            .between(
                !ObjectUtils.isEmpty(pageDTO.getMin()) || !ObjectUtils.isEmpty(pageDTO.getMax()),
                SkuInfoEntity::getPrice, ObjectUtils.isEmpty(pageDTO.getMin()) ? Integer.MIN_VALUE
                    : BigDecimal.valueOf(pageDTO.getMin()),
                ObjectUtils.isEmpty(pageDTO.getMax()) ? Integer.MAX_VALUE :
                    BigDecimal.valueOf(pageDTO.getMax()))
            .eq(!ObjectUtils.isEmpty(pageDTO.getBrandId()), SkuInfoEntity::getBrandId,
                pageDTO.getBrandId())
            .eq(!ObjectUtils.isEmpty(pageDTO.getCatalogId()), SkuInfoEntity::getCatalogId,
                pageDTO.getCatalogId())
            .and(!ObjectUtils.isEmpty(pageDTO.getKey()), w -> {
              w.eq(NumberUtils.isCreatable(pageDTO.getKey()), SkuInfoEntity::getSkuId,
                      Long.parseLong(pageDTO.getKey()))
                  .or()
                  .like(SkuInfoEntity::getSkuName, pageDTO.getKey());
            })
    );
  }

  @Override
  public List<SkuInfoEntity> getSkuBySpuId(Long spuId) {
    return this.list(new LambdaQueryWrapper<SkuInfoEntity>().eq(SkuInfoEntity::getSpuId, spuId));
  }

  @Override
  public SkuItemVo item(Long skuId) {
    return null;
  }

}




