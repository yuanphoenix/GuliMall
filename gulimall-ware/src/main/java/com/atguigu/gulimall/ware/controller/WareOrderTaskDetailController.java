package com.atguigu.gulimall.ware.controller;

import com.atguigu.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.atguigu.gulimall.ware.service.WareOrderTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 库存工作单 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/ware/wareOrderTaskDetail")
public class WareOrderTaskDetailController {

@Autowired
private WareOrderTaskDetailService wareOrderTaskDetailService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<WareOrderTaskDetailEntity> list = wareOrderTaskDetailService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    WareOrderTaskDetailEntity entity = wareOrderTaskDetailService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
    boolean saved = wareOrderTaskDetailService.save(wareOrderTaskDetail);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody WareOrderTaskDetailEntity wareOrderTaskDetail) {
    boolean updated = wareOrderTaskDetailService.updateById(wareOrderTaskDetail);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = wareOrderTaskDetailService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
