package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.gulimall.product.vo.AttrResponseVo;
import com.atguigu.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.PageDTO;
import utils.R;

import java.util.List;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<AttrEntity> list = attrService.list();
        return R.ok().put("data", list);
    }

    @GetMapping("/base/list/{catalogId}")
    public R baseList(@PathVariable Long catalogId, @ModelAttribute PageDTO pageDTO) {
        IPage<AttrResponseVo> attrEntityIPage = attrService.getBaseList(catalogId, pageDTO);
        return R.ok().put("data", attrEntityIPage);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        AttrEntity entity = attrService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        boolean saved = attrService.saveVo(attr);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody AttrEntity attr) {
        boolean updated = attrService.updateById(attr);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = attrService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
