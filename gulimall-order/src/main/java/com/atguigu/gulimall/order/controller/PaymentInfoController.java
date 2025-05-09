package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.PaymentInfoEntity;
import com.atguigu.gulimall.order.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 支付信息表 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/order/paymentInfo")
public class PaymentInfoController {

@Autowired
private PaymentInfoService paymentInfoService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<PaymentInfoEntity> list = paymentInfoService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    PaymentInfoEntity entity = paymentInfoService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody PaymentInfoEntity paymentInfo) {
    boolean saved = paymentInfoService.save(paymentInfo);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody PaymentInfoEntity paymentInfo) {
    boolean updated = paymentInfoService.updateById(paymentInfo);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = paymentInfoService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
