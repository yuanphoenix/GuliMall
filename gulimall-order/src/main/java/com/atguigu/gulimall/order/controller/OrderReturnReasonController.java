package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.OrderReturnReasonEntity;
import com.atguigu.gulimall.order.service.OrderReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 退货原因 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/order/orderReturnReason")
public class OrderReturnReasonController {

@Autowired
private OrderReturnReasonService orderReturnReasonService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<OrderReturnReasonEntity> list = orderReturnReasonService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    OrderReturnReasonEntity entity = orderReturnReasonService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody OrderReturnReasonEntity orderReturnReason) {
    boolean saved = orderReturnReasonService.save(orderReturnReason);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody OrderReturnReasonEntity orderReturnReason) {
    boolean updated = orderReturnReasonService.updateById(orderReturnReason);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = orderReturnReasonService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
