package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.SkuLadderEntity;
import com.atguigu.gulimall.coupon.service.SkuLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 商品阶梯价格 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/skuLadder")
public class SkuLadderController {

    @Autowired
    private SkuLadderService skuLadderService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<SkuLadderEntity> list = skuLadderService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SkuLadderEntity entity = skuLadderService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody SkuLadderEntity skuLadder) {
        boolean saved = skuLadderService.save(skuLadder);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody SkuLadderEntity skuLadder) {
        boolean updated = skuLadderService.updateById(skuLadder);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = skuLadderService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
