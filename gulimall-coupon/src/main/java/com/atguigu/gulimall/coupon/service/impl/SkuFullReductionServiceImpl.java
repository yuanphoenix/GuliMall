package com.atguigu.gulimall.coupon.service.impl;

import com.atguigu.gulimall.coupon.entity.MemberPriceEntity;
import com.atguigu.gulimall.coupon.entity.SkuFullReductionEntity;
import com.atguigu.gulimall.coupon.entity.SkuLadderEntity;
import com.atguigu.gulimall.coupon.mapper.SkuFullReductionMapper;
import com.atguigu.gulimall.coupon.service.MemberPriceService;
import com.atguigu.gulimall.coupon.service.SkuFullReductionService;
import com.atguigu.gulimall.coupon.service.SkuLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import to.SkuReducitionTo;

/**
 * @author tifa
 * @description 针对表【sms_sku_full_reduction(商品满减信息)】的数据库操作Service实现
 * @createDate 2025-05-08 21:07:54
 */
@Service
public class SkuFullReductionServiceImpl extends
    ServiceImpl<SkuFullReductionMapper, SkuFullReductionEntity>
    implements SkuFullReductionService {


  private final SkuLadderService skuLadderService;
  private final MemberPriceService memberPriceService;

  public SkuFullReductionServiceImpl(SkuLadderService skuLadderService,
      MemberPriceService memberPriceService) {
    this.skuLadderService = skuLadderService;
    this.memberPriceService = memberPriceService;
  }

  @Override
  public boolean saveInfoList(List<SkuReducitionTo> skuReducitionToList) {

    List<SkuLadderEntity> skuLadderEntityList = new ArrayList<>();

    List<SkuFullReductionEntity> skuFullReductionEntityList = new ArrayList<>();

    List<MemberPriceEntity> memberPriceEntityList = new ArrayList<>();

    skuReducitionToList.forEach(skuReducitionTo -> {
      //阶梯价格
      SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
      BeanUtils.copyProperties(skuReducitionTo, skuLadderEntity);
      skuLadderEntity.setAddOther(skuReducitionTo.getCountStatus());
      skuLadderEntityList.add(skuLadderEntity);

      SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
      BeanUtils.copyProperties(skuReducitionTo, skuFullReductionEntity);
      skuFullReductionEntity.setAddOther(skuReducitionTo.getPriceStatus());
      skuFullReductionEntityList.add(skuFullReductionEntity);

      memberPriceEntityList.addAll(

          Optional.ofNullable(skuReducitionTo.getMemberPrice()).orElse(Collections.emptyList())
              .stream()
              .map(price -> {
                    MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
                    memberPriceEntity.setSkuId(skuReducitionTo.getSkuId());
                    memberPriceEntity.setMemberLevelId(price.getId());
                    memberPriceEntity.setMemberLevelName(price.getName());
                    memberPriceEntity.setMemberPrice(price.getPrice());
                    memberPriceEntity.setAddOther(1);
                    return memberPriceEntity;
                  }
              ).toList());

    });

    skuLadderService.saveBatch(skuLadderEntityList);
    this.saveBatch(skuFullReductionEntityList);
    memberPriceService.saveBatch(memberPriceEntityList);
    return true;
  }
}




