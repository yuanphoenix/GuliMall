package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.OrderReturnApplyEntity;
import com.atguigu.gulimall.order.service.OrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 订单退货申请 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/order/orderReturnApply")
public class OrderReturnApplyController {

@Autowired
private OrderReturnApplyService orderReturnApplyService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<OrderReturnApplyEntity> list = orderReturnApplyService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    OrderReturnApplyEntity entity = orderReturnApplyService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody OrderReturnApplyEntity orderReturnApply) {
    boolean saved = orderReturnApplyService.save(orderReturnApply);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody OrderReturnApplyEntity orderReturnApply) {
    boolean updated = orderReturnApplyService.updateById(orderReturnApply);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = orderReturnApplyService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
