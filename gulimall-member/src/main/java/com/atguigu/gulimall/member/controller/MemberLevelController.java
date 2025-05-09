package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.entity.MemberLevelEntity;
import com.atguigu.gulimall.member.service.MemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
    * 会员等级 前端控制器
    * </p>
*
* @author tifa
* @since 2025-05-09
*/
@RestController
@RequestMapping("/member/memberLevel")
public class MemberLevelController {

@Autowired
private MemberLevelService memberLevelService;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List<MemberLevelEntity> list = memberLevelService.list();
    return R.ok().put("data", list);
    }

    /**
    * 根据ID获取数据
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
    MemberLevelEntity entity = memberLevelService.getById(id);
    return R.ok().put("data", entity);
    }

    /**
    * 保存数据
    */
    @PostMapping("/save")
    public R save(@RequestBody MemberLevelEntity memberLevel) {
    boolean saved = memberLevelService.save(memberLevel);
    return saved ? R.ok() : R.error();
    }

    /**
    * 修改数据
    */
    @PostMapping("/update")
    public R update(@RequestBody MemberLevelEntity memberLevel) {
    boolean updated = memberLevelService.updateById(memberLevel);
    return updated ? R.ok() : R.error();
    }

    /**
    * 删除数据
    */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
    boolean removed = memberLevelService.removeById(id);
    return removed ? R.ok() : R.error();
    }
    }
