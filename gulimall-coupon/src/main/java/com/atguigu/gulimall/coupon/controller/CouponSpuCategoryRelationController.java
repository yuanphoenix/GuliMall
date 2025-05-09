package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.CouponSpuCategoryRelationEntity;
import com.atguigu.gulimall.coupon.service.CouponSpuCategoryRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 优惠券分类关联 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/coupon/couponSpuCategoryRelation")
public class CouponSpuCategoryRelationController {

@Autowired
private CouponSpuCategoryRelationService couponSpuCategoryRelationService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<CouponSpuCategoryRelationEntity> list = couponSpuCategoryRelationService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    CouponSpuCategoryRelationEntity entity = couponSpuCategoryRelationService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody CouponSpuCategoryRelationEntity couponSpuCategoryRelation) {
    boolean saved = couponSpuCategoryRelationService.save(couponSpuCategoryRelation);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody CouponSpuCategoryRelationEntity couponSpuCategoryRelation) {
    boolean updated = couponSpuCategoryRelationService.updateById(couponSpuCategoryRelation);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = couponSpuCategoryRelationService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
