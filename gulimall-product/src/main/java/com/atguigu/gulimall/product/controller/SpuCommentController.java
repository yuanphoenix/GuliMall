package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.SpuCommentEntity;
import com.atguigu.gulimall.product.service.SpuCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 商品评价 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/product/spuComment")
public class SpuCommentController {

@Autowired
private SpuCommentService spuCommentService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<SpuCommentEntity> list = spuCommentService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    SpuCommentEntity entity = spuCommentService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody SpuCommentEntity spuComment) {
    boolean saved = spuCommentService.save(spuComment);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody SpuCommentEntity spuComment) {
    boolean updated = spuCommentService.updateById(spuComment);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = spuCommentService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
