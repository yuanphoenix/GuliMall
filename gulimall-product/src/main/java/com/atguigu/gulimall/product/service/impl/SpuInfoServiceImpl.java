package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.ProductAttrValueEntity;
import com.atguigu.gulimall.product.entity.SkuImagesEntity;
import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.atguigu.gulimall.product.entity.SpuImagesEntity;
import com.atguigu.gulimall.product.entity.SpuInfoDescEntity;
import com.atguigu.gulimall.product.entity.SpuInfoEntity;
import com.atguigu.gulimall.product.feign.CouponFeignService;
import com.atguigu.gulimall.product.mapper.AttrMapper;
import com.atguigu.gulimall.product.mapper.ProductAttrValueMapper;
import com.atguigu.gulimall.product.mapper.SkuImagesMapper;
import com.atguigu.gulimall.product.mapper.SkuInfoMapper;
import com.atguigu.gulimall.product.mapper.SkuSaleAttrValueMapper;
import com.atguigu.gulimall.product.mapper.SpuImagesMapper;
import com.atguigu.gulimall.product.mapper.SpuInfoDescMapper;
import com.atguigu.gulimall.product.mapper.SpuInfoMapper;
import com.atguigu.gulimall.product.service.SpuInfoService;
import com.atguigu.gulimall.product.vo.SpuPageVo;
import com.atguigu.gulimall.product.vo.spuinfo.BaseAttrs;
import com.atguigu.gulimall.product.vo.spuinfo.Images;
import com.atguigu.gulimall.product.vo.spuinfo.Skus;
import com.atguigu.gulimall.product.vo.spuinfo.SpuInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import to.SkuReducitionTo;
import to.SpuBoundsTo;
import utils.PageUtils;
import utils.R;

/**
 * @author tifa
 * @description 针对表【pms_spu_info(spu信息)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfoEntity>
    implements SpuInfoService {

  private final SpuInfoDescMapper spuInfoDescMapper;
  private final SpuImagesMapper spuImagesMapper;


  private final AttrMapper attrMapper;
  private final ProductAttrValueMapper productAttrValueMapper;
  private final SkuInfoMapper skuInfoMapper;
  private final SkuImagesMapper skuImagesMapper;
  private final SkuSaleAttrValueMapper skuSaleAttrValueMapper;

  private final CouponFeignService couponFeignService;

  public SpuInfoServiceImpl(SpuInfoDescMapper spuInfoDescMapper, SpuImagesMapper spuImagesMapper,
      AttrMapper attrMapper, ProductAttrValueMapper productAttrValueMapper,
      SkuInfoMapper skuInfoMapper, SkuImagesMapper skuImagesMapper,
      SkuSaleAttrValueMapper skuSaleAttrValueMapper, CouponFeignService couponFeignService) {

    this.spuInfoDescMapper = spuInfoDescMapper;
    this.spuImagesMapper = spuImagesMapper;
    this.attrMapper = attrMapper;
    this.productAttrValueMapper = productAttrValueMapper;
    this.skuInfoMapper = skuInfoMapper;
    this.skuImagesMapper = skuImagesMapper;
    this.skuSaleAttrValueMapper = skuSaleAttrValueMapper;
    this.couponFeignService = couponFeignService;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean saveSpu(SpuInfoVo spuInfoVo) {
    if (Objects.isNull(spuInfoVo)) {
      return false;
    }

    //最外面的核心spu，要转为下面这个 SpuInfoEntity。  操作 spu_info
    SpuInfoEntity spuInfo = new SpuInfoEntity();
    BeanUtils.copyProperties(spuInfoVo, spuInfo);
    save(spuInfo);
    //保存decript，这里面是图片介绍.操作  spu_info_desc
    List<String> decriptList = spuInfoVo.getDecript();
    SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
    spuInfoDescEntity.setSpuId(spuInfo.getId());
    spuInfoDescEntity.setDecript(String.join(",", decriptList));
    spuInfoDescMapper.insert(spuInfoDescEntity);
    //保存SPU的图片集合 。操作spu_images

    List<String> images = spuInfoVo.getImages();
    List<SpuImagesEntity> spuImagesEntityList = images.stream()
        .filter(imgUrl -> !ObjectUtils.isEmpty(imgUrl))
        .map(imgUrl -> {
          SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
          //这里的这个字段，只保存了图片的url以及图片涉及到的spu的id
          spuImagesEntity.setSpuId(spuInfo.getId());
          spuImagesEntity.setImgUrl(imgUrl);
          return spuImagesEntity;
        }).toList();
    spuImagesMapper.insert(spuImagesEntityList);
    //保存SPU的规格参数  pms_product_attr_value
    List<BaseAttrs> baseAttrs = spuInfoVo.getBaseAttrs();
    List<ProductAttrValueEntity> productAttrValueEntityList = baseAttrs.stream().map(baseAttr -> {
      ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
      BeanUtils.copyProperties(baseAttr, productAttrValueEntity);
      AttrEntity attrEntity = attrMapper.selectById(productAttrValueEntity.getAttrId());
      Optional.ofNullable(attrEntity)
          .map(AttrEntity::getAttrName)
          .ifPresent(productAttrValueEntity::setAttrName);
      productAttrValueEntity.setSpuId(spuInfo.getId());
      productAttrValueEntity.setQuickShow(baseAttr.getShowDesc());
      return productAttrValueEntity;
    }).toList();
    productAttrValueMapper.insert(productAttrValueEntityList);
    //保存spu对应的sku信息。然而sku又有很多信息
    List<Skus> skus = spuInfoVo.getSkus();
    List<SkuInfoEntity> skuInfoEntityList = skus.stream().map(item -> {
      SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
      BeanUtils.copyProperties(item, skuInfoEntity);
      skuInfoEntity.setBrandId(spuInfoVo.getBrandId());
      skuInfoEntity.setCatalogId(spuInfoVo.getCatalogId());
      skuInfoEntity.setSaleCount(0L);
      skuInfoEntity.setSpuId(spuInfo.getId());

      Optional.ofNullable(item.getImages()).orElse(Collections.emptyList())
          .stream()
          .filter(a -> a.getDefaultImg() == 1)
          .findFirst()
          .map(Images::getImgUrl)
          .ifPresent(skuInfoEntity::setSkuDefaultImg);
      return skuInfoEntity;
    }).toList();
    //sku的基本信息  sku_info
    skuInfoMapper.insert(skuInfoEntityList);
    //sku的图片信息 sku_images
    List<SkuImagesEntity> skuImagesEntityList = new ArrayList<>();
    List<SkuSaleAttrValueEntity> skuSaleAttrValueEntityList = new ArrayList<>();
    List<SkuReducitionTo> skuReducitionToList = new ArrayList<>();
    assert skuInfoEntityList.size() == skus.size();
    for (int i = 0; i < skuInfoEntityList.size(); i++) {
      Long skuId = skuInfoEntityList.get(i).getSkuId();
      Skus skuItem = skus.get(i);

      skuImagesEntityList.addAll(
          Optional.ofNullable(skuItem.getImages())
              .orElse(Collections.emptyList())
              .stream()
              .filter(imgUrl -> !ObjectUtils.isEmpty(imgUrl.getImgUrl()))
              .map(imageItem -> {
                SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                skuImagesEntity.setSkuId(skuId);
                BeanUtils.copyProperties(imageItem, skuImagesEntity);
                return skuImagesEntity;
              }).toList());

      skuSaleAttrValueEntityList.addAll(Optional.ofNullable(skuItem.getAttr())
          .orElse(Collections.emptyList())
          .stream()
          .map(attrItem -> {
            SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
            BeanUtils.copyProperties(attrItem, skuSaleAttrValueEntity);
            skuSaleAttrValueEntity.setSkuId(skuId);
            return skuSaleAttrValueEntity;
          }).toList());

      SkuReducitionTo skuReducitionTo = new SkuReducitionTo();
      BeanUtils.copyProperties(skuItem, skuReducitionTo);
      //注意BeanUtils拷贝不了 List,需要手动去拷贝
      skuReducitionTo.setMemberPrice(Optional.ofNullable(skuItem.getMemberPrice())
          .orElse(Collections.emptyList())
          .stream()
          .map(temp -> {
            to.MemberPrice memberPrice1 = new to.MemberPrice();
            BeanUtils.copyProperties(temp, memberPrice1);
            return memberPrice1;
          }).toList());
      skuReducitionTo.setSkuId(skuId);
      skuReducitionToList.add(skuReducitionTo);
    }
    skuSaleAttrValueMapper.insert(skuSaleAttrValueEntityList);
    skuImagesMapper.insert(skuImagesEntityList);

    //sku的优惠信息。积分。满减优惠
    SpuBoundsTo spuBoundsTo = new SpuBoundsTo();
    BeanUtils.copyProperties(spuInfoVo.getBounds(), spuBoundsTo);
    spuBoundsTo.setSpuId(spuInfoDescEntity.getSpuId());

    R r = couponFeignService.saveBounds(spuBoundsTo);
    if (r.getCode() != 0) {
      throw new RuntimeException("远程调用saveBounds错误");
    }

    r = couponFeignService.saveSkuReduction(skuReducitionToList);

    if (r.getCode() != 0) {
      throw new RuntimeException("aveSkuReduction远程调用错误");
    }

    return true;
  }

  @Override
  public IPage<SpuInfoEntity> pageWithCondition(SpuPageVo pageDTO) {

    //使用mybatis-plus中的conditon来避免传送多余的SQL语句。
    return baseMapper.selectPage(PageUtils.of(pageDTO),
        new LambdaQueryWrapper<SpuInfoEntity>()

            .eq(!ObjectUtils.isEmpty(pageDTO.getBrandId()), SpuInfoEntity::getBrandId,
                pageDTO.getBrandId())
            .eq(!ObjectUtils.isEmpty(pageDTO.getCatalogId()), SpuInfoEntity::getCatalogId,
                pageDTO.getCatalogId())
            .eq(!ObjectUtils.isEmpty(pageDTO.getStatus()), SpuInfoEntity::getPublishStatus,
                pageDTO.getStatus())

            .and(!ObjectUtils.isEmpty(pageDTO.getKey()), w -> {
              w.eq(NumberUtils.isCreatable(pageDTO.getKey()), SpuInfoEntity::getId,
                      Long.parseLong(pageDTO.getKey()))
                  .or()
                  .like(SpuInfoEntity::getSpuName, pageDTO.getKey());
            })
    );
  }

}