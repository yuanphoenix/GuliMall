package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.RefundInfoEntity;
import com.atguigu.gulimall.order.service.RefundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 退款信息 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/order/refundInfo")
public class RefundInfoController {

    @Autowired
    private RefundInfoService refundInfoService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<RefundInfoEntity> list = refundInfoService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        RefundInfoEntity entity = refundInfoService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody RefundInfoEntity refundInfo) {
        boolean saved = refundInfoService.save(refundInfo);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody RefundInfoEntity refundInfo) {
        boolean updated = refundInfoService.updateById(refundInfo);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = refundInfoService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
