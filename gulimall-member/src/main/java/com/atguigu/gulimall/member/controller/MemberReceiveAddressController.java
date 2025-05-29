package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.entity.MemberReceiveAddressEntity;
import com.atguigu.gulimall.member.service.MemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 会员收货地址 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/member/memberReceiveAddress")
public class MemberReceiveAddressController {

    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<MemberReceiveAddressEntity> list = memberReceiveAddressService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MemberReceiveAddressEntity entity = memberReceiveAddressService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
        boolean saved = memberReceiveAddressService.save(memberReceiveAddress);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
        boolean updated = memberReceiveAddressService.updateById(memberReceiveAddress);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = memberReceiveAddressService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
