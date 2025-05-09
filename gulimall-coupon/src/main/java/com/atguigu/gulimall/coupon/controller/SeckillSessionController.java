package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.SeckillSessionEntity;
import com.atguigu.gulimall.coupon.service.SeckillSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 秒杀活动场次 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/coupon/seckillSession")
public class SeckillSessionController {

@Autowired
private SeckillSessionService seckillSessionService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<SeckillSessionEntity> list = seckillSessionService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    SeckillSessionEntity entity = seckillSessionService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody SeckillSessionEntity seckillSession) {
    boolean saved = seckillSessionService.save(seckillSession);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody SeckillSessionEntity seckillSession) {
    boolean updated = seckillSessionService.updateById(seckillSession);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = seckillSessionService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
