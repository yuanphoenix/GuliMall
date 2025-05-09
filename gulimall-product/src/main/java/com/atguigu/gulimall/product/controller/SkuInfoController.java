package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * sku信息 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/product/skuInfo")
public class SkuInfoController {

@Autowired
private SkuInfoService skuInfoService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<SkuInfoEntity> list = skuInfoService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    SkuInfoEntity entity = skuInfoService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody SkuInfoEntity skuInfo) {
    boolean saved = skuInfoService.save(skuInfo);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody SkuInfoEntity skuInfo) {
    boolean updated = skuInfoService.updateById(skuInfo);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = skuInfoService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
