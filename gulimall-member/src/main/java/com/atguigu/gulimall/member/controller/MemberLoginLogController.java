package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.entity.MemberLoginLogEntity;
import com.atguigu.gulimall.member.service.MemberLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 会员登录记录 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/member/memberLoginLog")
public class MemberLoginLogController {

@Autowired
private MemberLoginLogService memberLoginLogService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<MemberLoginLogEntity> list = memberLoginLogService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    MemberLoginLogEntity entity = memberLoginLogService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody MemberLoginLogEntity memberLoginLog) {
    boolean saved = memberLoginLogService.save(memberLoginLog);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody MemberLoginLogEntity memberLoginLog) {
    boolean updated = memberLoginLogService.updateById(memberLoginLog);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = memberLoginLogService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
