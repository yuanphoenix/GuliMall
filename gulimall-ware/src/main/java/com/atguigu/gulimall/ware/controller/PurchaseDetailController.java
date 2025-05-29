package com.atguigu.gulimall.ware.controller;

import com.atguigu.gulimall.ware.entity.PurchaseDetailEntity;
import com.atguigu.gulimall.ware.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 采购需求 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/ware/purchaseDetail")
public class PurchaseDetailController {

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<PurchaseDetailEntity> list = purchaseDetailService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        PurchaseDetailEntity entity = purchaseDetailService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody PurchaseDetailEntity purchaseDetail) {
        boolean saved = purchaseDetailService.save(purchaseDetail);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody PurchaseDetailEntity purchaseDetail) {
        boolean updated = purchaseDetailService.updateById(purchaseDetail);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = purchaseDetailService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
