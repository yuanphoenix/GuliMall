package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utils.PageDTO;
import utils.R;
import valid.AddWithDefaultGroup;
import valid.UpdateGroup;

import java.util.List;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/brand")
public class BrandController {

    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private BrandService brandService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<BrandEntity> list = brandService.list();
        return R.ok().put("data", list);
    }

    /**
     * 获取所有数据
     */
    @GetMapping("/list/page")
    public R listPage(@ModelAttribute PageDTO pageDTO) {
        IPage<BrandEntity> brandEntityIPage = brandService.listPage(pageDTO);
        return R.ok().put("data", brandEntityIPage);
    }


    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        BrandEntity entity = brandService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(
            @Validated(value = {AddWithDefaultGroup.class}) @RequestBody BrandEntity brand) {
        boolean saved = brandService.save(brand);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand) {
        boolean updated = brandService.updateById(brand);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = brandService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
