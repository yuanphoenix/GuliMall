package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.SpuInfoDescEntity;
import com.atguigu.gulimall.product.entity.SpuInfoEntity;
import com.atguigu.gulimall.product.mapper.SpuInfoDescMapper;
import com.atguigu.gulimall.product.mapper.SpuInfoMapper;
import com.atguigu.gulimall.product.service.SpuInfoService;
import com.atguigu.gulimall.product.vo.spuinfo.SpuInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tifa
 * @description 针对表【pms_spu_info(spu信息)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfoEntity>
    implements SpuInfoService {

  @Autowired
  private SpuInfoDescMapper spuInfoDescMapper;

  @Transactional
  @Override
  public boolean saveSpu(SpuInfoVo spuInfoVo) {
    //最外面的核心spu，要转为下面这个 SpuInfoEntity。  操作 spu_info
    SpuInfoEntity spuInfo = new SpuInfoEntity();
    BeanUtils.copyProperties(spuInfoVo, spuInfo);
    spuInfo.setCreateTime(LocalDateTime.now());
    spuInfo.setUpdateTime(LocalDateTime.now());
    save(spuInfo);
    //保存decript，这里面是图片介绍.操作  spu_info_desc
    List<String> decriptList = spuInfoVo.getDecript();
    List<SpuInfoDescEntity> spuInfoDescEntityList = decriptList.stream().map(decript -> {
      SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
      spuInfoDescEntity.setSpuId(spuInfo.getId());
      spuInfoDescEntity.setDecript(decript);
      return spuInfoDescEntity;
    }).toList();
    spuInfoDescMapper.insert(spuInfoDescEntityList);
    //保存SPU的图片集合 。操作spu_images

    //保存SPU的规格参数  product_attr_value


    
    //保存spu对应的sku信息。然而sku又有很多信息
    //sku的基本信息  sku_info
    //sku的图片信息 sku_images
    //sku的销售属性 sku_sale_attr_value
    //sku的优惠信息

    return false;
  }
}




