package com.atguigu.gulimall.ware.controller;

import com.atguigu.gulimall.ware.entity.PurchaseEntity;
import com.atguigu.gulimall.ware.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 采购单 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/ware/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<PurchaseEntity> list = purchaseService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        PurchaseEntity entity = purchaseService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody PurchaseEntity purchase) {
        boolean saved = purchaseService.save(purchase);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody PurchaseEntity purchase) {
        boolean updated = purchaseService.updateById(purchase);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = purchaseService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
