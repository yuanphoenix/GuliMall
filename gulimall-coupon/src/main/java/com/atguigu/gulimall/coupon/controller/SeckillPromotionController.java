package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.SeckillPromotionEntity;
import com.atguigu.gulimall.coupon.service.SeckillPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 秒杀活动 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/coupon/seckillPromotion")
public class SeckillPromotionController {

@Autowired
private SeckillPromotionService seckillPromotionService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<SeckillPromotionEntity> list = seckillPromotionService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    SeckillPromotionEntity entity = seckillPromotionService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody SeckillPromotionEntity seckillPromotion) {
    boolean saved = seckillPromotionService.save(seckillPromotion);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody SeckillPromotionEntity seckillPromotion) {
    boolean updated = seckillPromotionService.updateById(seckillPromotion);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = seckillPromotionService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
