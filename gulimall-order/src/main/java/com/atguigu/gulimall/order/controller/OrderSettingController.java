package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.OrderSettingEntity;
import com.atguigu.gulimall.order.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 订单配置信息 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/order/orderSetting")
public class OrderSettingController {

    @Autowired
    private OrderSettingService orderSettingService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<OrderSettingEntity> list = orderSettingService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        OrderSettingEntity entity = orderSettingService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody OrderSettingEntity orderSetting) {
        boolean saved = orderSettingService.save(orderSetting);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody OrderSettingEntity orderSetting) {
        boolean updated = orderSettingService.updateById(orderSetting);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = orderSettingService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
