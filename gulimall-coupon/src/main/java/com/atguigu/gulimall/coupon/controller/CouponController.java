package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.atguigu.gulimall.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */

@RefreshScope
@RestController
@RequestMapping("/coupon/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<CouponEntity> list = couponService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CouponEntity entity = couponService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody CouponEntity coupon) {
        boolean saved = couponService.save(coupon);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody CouponEntity coupon) {
        boolean updated = couponService.updateById(coupon);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = couponService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
