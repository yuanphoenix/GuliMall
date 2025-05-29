package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.entity.GrowthChangeHistoryEntity;
import com.atguigu.gulimall.member.service.GrowthChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 成长值变化历史记录 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/member/growthChangeHistory")
public class GrowthChangeHistoryController {

    @Autowired
    private GrowthChangeHistoryService growthChangeHistoryService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<GrowthChangeHistoryEntity> list = growthChangeHistoryService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        GrowthChangeHistoryEntity entity = growthChangeHistoryService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody GrowthChangeHistoryEntity growthChangeHistory) {
        boolean saved = growthChangeHistoryService.save(growthChangeHistory);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody GrowthChangeHistoryEntity growthChangeHistory) {
        boolean updated = growthChangeHistoryService.updateById(growthChangeHistory);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = growthChangeHistoryService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
