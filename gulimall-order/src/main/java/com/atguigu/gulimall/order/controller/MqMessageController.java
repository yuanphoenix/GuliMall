package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.MqMessageEntity;
import com.atguigu.gulimall.order.service.MqMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    *  前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/order/mqMessage")
public class MqMessageController {

@Autowired
private MqMessageService mqMessageService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<MqMessageEntity> list = mqMessageService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    MqMessageEntity entity = mqMessageService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody MqMessageEntity mqMessage) {
    boolean saved = mqMessageService.save(mqMessage);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody MqMessageEntity mqMessage) {
    boolean updated = mqMessageService.updateById(mqMessage);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = mqMessageService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
