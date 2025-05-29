package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.atguigu.gulimall.coupon.service.SeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 秒杀活动商品关联 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/seckillSkuRelation")
public class SeckillSkuRelationController {

    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<SeckillSkuRelationEntity> list = seckillSkuRelationService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SeckillSkuRelationEntity entity = seckillSkuRelationService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
        boolean saved = seckillSkuRelationService.save(seckillSkuRelation);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody SeckillSkuRelationEntity seckillSkuRelation) {
        boolean updated = seckillSkuRelationService.updateById(seckillSkuRelation);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = seckillSkuRelationService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
