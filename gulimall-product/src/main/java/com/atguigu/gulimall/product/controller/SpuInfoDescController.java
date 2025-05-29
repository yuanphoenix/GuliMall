package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.SpuInfoDescEntity;
import com.atguigu.gulimall.product.service.SpuInfoDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * spu信息介绍 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/spuInfoDesc")
public class SpuInfoDescController {

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<SpuInfoDescEntity> list = spuInfoDescService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        SpuInfoDescEntity entity = spuInfoDescService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody SpuInfoDescEntity spuInfoDesc) {
        boolean saved = spuInfoDescService.save(spuInfoDesc);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody SpuInfoDescEntity spuInfoDesc) {
        boolean updated = spuInfoDescService.updateById(spuInfoDesc);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = spuInfoDescService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
