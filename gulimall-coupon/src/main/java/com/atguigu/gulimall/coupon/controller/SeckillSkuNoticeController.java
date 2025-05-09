package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.SeckillSkuNoticeEntity;
import com.atguigu.gulimall.coupon.service.SeckillSkuNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 秒杀商品通知订阅 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/coupon/seckillSkuNotice")
public class SeckillSkuNoticeController {

@Autowired
private SeckillSkuNoticeService seckillSkuNoticeService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<SeckillSkuNoticeEntity> list = seckillSkuNoticeService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    SeckillSkuNoticeEntity entity = seckillSkuNoticeService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice) {
    boolean saved = seckillSkuNoticeService.save(seckillSkuNotice);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice) {
    boolean updated = seckillSkuNoticeService.updateById(seckillSkuNotice);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = seckillSkuNoticeService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
