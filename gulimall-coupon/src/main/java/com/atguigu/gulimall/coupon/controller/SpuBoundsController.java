package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.SpuBoundsEntity;
import com.atguigu.gulimall.coupon.service.SpuBoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 商品spu积分设置 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/spuBounds")
public class SpuBoundsController {

    @Autowired
    private SpuBoundsService spuBoundsService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<SpuBoundsEntity> list = spuBoundsService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SpuBoundsEntity entity = spuBoundsService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody SpuBoundsEntity spuBounds) {
        boolean saved = spuBoundsService.save(spuBounds);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody SpuBoundsEntity spuBounds) {
        boolean updated = spuBoundsService.updateById(spuBounds);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = spuBoundsService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
