package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.HomeSubjectSpuEntity;
import com.atguigu.gulimall.coupon.service.HomeSubjectSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 专题商品 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/coupon/homeSubjectSpu")
public class HomeSubjectSpuController {

@Autowired
private HomeSubjectSpuService homeSubjectSpuService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<HomeSubjectSpuEntity> list = homeSubjectSpuService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    HomeSubjectSpuEntity entity = homeSubjectSpuService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody HomeSubjectSpuEntity homeSubjectSpu) {
    boolean saved = homeSubjectSpuService.save(homeSubjectSpu);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody HomeSubjectSpuEntity homeSubjectSpu) {
    boolean updated = homeSubjectSpuService.updateById(homeSubjectSpu);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = homeSubjectSpuService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
