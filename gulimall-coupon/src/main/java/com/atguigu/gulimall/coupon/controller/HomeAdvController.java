package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.HomeAdvEntity;
import com.atguigu.gulimall.coupon.service.HomeAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 首页轮播广告 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/homeAdv")
public class HomeAdvController {

    @Autowired
    private HomeAdvService homeAdvService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<HomeAdvEntity> list = homeAdvService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        HomeAdvEntity entity = homeAdvService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody HomeAdvEntity homeAdv) {
        boolean saved = homeAdvService.save(homeAdv);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody HomeAdvEntity homeAdv) {
        boolean updated = homeAdvService.updateById(homeAdv);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = homeAdvService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
