package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.entity.IntegrationChangeHistoryEntity;
import com.atguigu.gulimall.member.service.IntegrationChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 积分变化历史记录 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/member/integrationChangeHistory")
public class IntegrationChangeHistoryController {

    @Autowired
    private IntegrationChangeHistoryService integrationChangeHistoryService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<IntegrationChangeHistoryEntity> list = integrationChangeHistoryService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        IntegrationChangeHistoryEntity entity = integrationChangeHistoryService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {
        boolean saved = integrationChangeHistoryService.save(integrationChangeHistory);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody IntegrationChangeHistoryEntity integrationChangeHistory) {
        boolean updated = integrationChangeHistoryService.updateById(integrationChangeHistory);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = integrationChangeHistoryService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
