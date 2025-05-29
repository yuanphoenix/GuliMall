package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.MemberPriceEntity;
import com.atguigu.gulimall.coupon.service.MemberPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 商品会员价格 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/memberPrice")
public class MemberPriceController {

    @Autowired
    private MemberPriceService memberPriceService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<MemberPriceEntity> list = memberPriceService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MemberPriceEntity entity = memberPriceService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberPriceEntity memberPrice) {
        boolean saved = memberPriceService.save(memberPrice);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody MemberPriceEntity memberPrice) {
        boolean updated = memberPriceService.updateById(memberPrice);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = memberPriceService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
