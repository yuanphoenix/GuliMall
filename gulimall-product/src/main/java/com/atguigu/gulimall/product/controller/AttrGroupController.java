package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.PageDTO;
import utils.R;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list/{catalogId}")
    public R list(@PathVariable Long catalogId, @ModelAttribute PageDTO attrGroupQueryDTO) {

        IPage<AttrGroupEntity> attrGroupEntityIPage = attrGroupService.queryPage(attrGroupQueryDTO, catalogId);
        return R.ok().put("data", attrGroupEntityIPage);
    }

    /**
     * 获取所有数据
     */
    @GetMapping("/list/page")
    public R listPage(@ModelAttribute PageDTO attrGroupQueryDTO) {
        IPage<AttrGroupEntity> attrGroupEntityIPage = attrGroupService.queryPage(attrGroupQueryDTO);
        return R.ok().put("data", attrGroupEntityIPage);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        AttrGroupEntity entity = attrGroupService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        boolean saved = attrGroupService.save(attrGroup);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        boolean updated = attrGroupService.updateById(attrGroup);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = attrGroupService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
