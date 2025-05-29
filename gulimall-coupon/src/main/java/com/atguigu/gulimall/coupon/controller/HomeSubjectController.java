package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.HomeSubjectEntity;
import com.atguigu.gulimall.coupon.service.HomeSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/homeSubject")
public class HomeSubjectController {

    @Autowired
    private HomeSubjectService homeSubjectService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<HomeSubjectEntity> list = homeSubjectService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        HomeSubjectEntity entity = homeSubjectService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody HomeSubjectEntity homeSubject) {
        boolean saved = homeSubjectService.save(homeSubject);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody HomeSubjectEntity homeSubject) {
        boolean updated = homeSubjectService.updateById(homeSubject);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = homeSubjectService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
