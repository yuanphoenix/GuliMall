package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.SkuImagesEntity;
import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.atguigu.gulimall.product.entity.SpuInfoDescEntity;
import com.atguigu.gulimall.product.mapper.SkuInfoMapper;
import com.atguigu.gulimall.product.service.SkuImagesService;
import com.atguigu.gulimall.product.service.SkuInfoService;
import com.atguigu.gulimall.product.service.SkuSaleAttrValueService;
import com.atguigu.gulimall.product.service.SpuInfoDescService;
import com.atguigu.gulimall.product.vo.SkuItemVo;
import com.atguigu.gulimall.product.vo.SkuItemVo.ItemSaleAttrVo;
import com.atguigu.gulimall.product.vo.SkuItemVo.SaleAttrValueVo;
import com.atguigu.gulimall.product.vo.SkuItemVo.SpuBaseAttrVo;
import com.atguigu.gulimall.product.vo.SkuItemVo.SpuItemBaseAttrFlatDTO;
import com.atguigu.gulimall.product.vo.SkuItemVo.SpuItemBaseAttrVo;
import com.atguigu.gulimall.product.vo.SpuPageVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

  private final ThreadPoolExecutor executor;

  private final SpuInfoDescService spuInfoDescService;

  private final SkuImagesService skuImagesService;

  @Autowired
  private SkuSaleAttrValueService skuSaleAttrValueService;

  public SkuInfoServiceImpl(SpuInfoDescService spuInfoDescService,
      SkuImagesService skuImagesService, ThreadPoolExecutor executor) {
    this.spuInfoDescService = spuInfoDescService;
    this.skuImagesService = skuImagesService;
    this.executor = executor;
  }

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
    SkuItemVo skuItemVo = new SkuItemVo();
    CompletableFuture<SkuInfoEntity> skuInfoFuture = CompletableFuture.supplyAsync(
        () -> baseMapper.selectById(skuId), executor).thenApply(sku -> {
      if (sku == null) {
        log.error("sku实体为空");
        throw new NoSuchElementException("sku没找到" + skuId);
      }
      skuItemVo.setSkuInfoEntity(sku);
      return sku;
    });

    var imagesFuture = CompletableFuture.supplyAsync(() -> {
      //我的数据库skuImage只有一个图片
      return skuImagesService.list(
          new LambdaQueryWrapper<SkuImagesEntity>().eq(SkuImagesEntity::getSkuId, skuId));
    }, executor).thenAccept(skuItemVo::setImagesEntities).exceptionally(ex -> {
      log.error("没能设置sku图片" + skuId, ex);
      skuItemVo.setImagesEntities(Collections.emptyList());
      return null;
    });

    var descFuture = skuInfoFuture.thenApplyAsync(skuInfoEntity -> spuInfoDescService.getOne(
            new LambdaQueryWrapper<SpuInfoDescEntity>().eq(SpuInfoDescEntity::getSpuId,
                skuInfoEntity.getSpuId())), executor)
        .thenAccept(skuItemVo::setDesp)
        .exceptionally(ex -> {
          log.error("spu描述异常" + skuId, ex);
          skuItemVo.setDesp(null);
          return null;
        });

    var saleAttrFuture = skuInfoFuture.thenAcceptAsync(skuInfoEntity -> {
      //查出这个spu下一共有多少sku
      List<Long> skuIds = baseMapper.selectList(
          new LambdaQueryWrapper<SkuInfoEntity>().eq(SkuInfoEntity::getSpuId,
                  skuInfoEntity.getSpuId())
              .select(SkuInfoEntity::getSkuId)).stream().map(SkuInfoEntity::getSkuId).toList();

      //根据这些sku查出销售属性
      List<SkuSaleAttrValueEntity> skuSaleAttrValueEntityList = skuSaleAttrValueService.list(
          new LambdaQueryWrapper<SkuSaleAttrValueEntity>().in(SkuSaleAttrValueEntity::getSkuId,
              skuIds));

      //设置销售属性
      List<ItemSaleAttrVo> itemSaleAttrVoList = new ArrayList<>();
      skuSaleAttrValueEntityList.stream()
          .collect(Collectors.groupingBy(SkuSaleAttrValueEntity::getAttrName))
          .forEach((attrName, group) -> {
            ItemSaleAttrVo saleAttrVo = new ItemSaleAttrVo();
            saleAttrVo.setAttrId(group.getFirst().getAttrId());
            saleAttrVo.setAttrName(attrName);
            List<SaleAttrValueVo> attrValues = group.stream()
                .collect(Collectors.groupingBy(SkuSaleAttrValueEntity::getAttrValue))
                .entrySet().stream().
                map(entry -> {
                  SaleAttrValueVo vvo = new SaleAttrValueVo();
                  vvo.setAttrValue(entry.getKey());
                  vvo.setSkuIds(
                      entry.getValue().stream().map(SkuSaleAttrValueEntity::getSkuId)
                          .toList());
                  return vvo;
                }).toList();

            saleAttrVo.setAttrValues(attrValues);
            itemSaleAttrVoList.add(saleAttrVo);
          });

      skuItemVo.setSaleAttrVos(itemSaleAttrVoList);

    }, executor).exceptionally(ex -> {
      log.error("销售属性失败" + skuId, ex);
      skuItemVo.setSaleAttrVos(Collections.emptyList());
      return null;
    });

    var baseAttrFuture = skuInfoFuture.thenAcceptAsync(skuInfoEntity -> {
      //设置基本属性
      List<SpuItemBaseAttrVo> itemBaseAttrVos = convertToGroupedVO(
          baseMapper.getspuItemBaseAttr(skuInfoEntity.getSpuId()));
      skuItemVo.setGroupAttrVos(itemBaseAttrVos);
    }, executor).exceptionally(ex -> {
      log.error("基础属性失败" + skuId, ex);
      skuItemVo.setGroupAttrVos(Collections.emptyList());
      return null;
    });

    CompletableFuture.allOf(descFuture, imagesFuture, saleAttrFuture, baseAttrFuture).join();
    return skuItemVo;
  }


  /**
   *
   * 因为从数据库查出来的是扁平SpuItemBaseAttrTo，所以需要这个方法转换一下
   *
   * @param spuItemBaseAttrFlatDTOS
   * @return
   */
  private List<SpuItemBaseAttrVo> convertToGroupedVO(
      List<SpuItemBaseAttrFlatDTO> spuItemBaseAttrFlatDTOS) {
    Map<String, SpuItemBaseAttrVo> map = new HashMap<>(25);
    for (SpuItemBaseAttrFlatDTO s : spuItemBaseAttrFlatDTOS) {
      SpuItemBaseAttrVo orDefault = map.getOrDefault(s.getGroupName(), new SpuItemBaseAttrVo());
      if (ObjectUtils.isEmpty(orDefault.getSpuBaseAttrVoList())) {
        map.put(s.getGroupName(), orDefault);
        orDefault.setSpuBaseAttrVoList(new ArrayList<>());
        orDefault.setGroupName(s.getGroupName());
      }
      SpuBaseAttrVo spuBaseAttrVo = new SpuBaseAttrVo();
      BeanUtils.copyProperties(s, spuBaseAttrVo);
      orDefault.getSpuBaseAttrVoList().add(spuBaseAttrVo);
    }
    return new ArrayList<>(map.values());
  }

}




