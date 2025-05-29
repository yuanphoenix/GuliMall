package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.entity.MemberCollectSpuEntity;
import com.atguigu.gulimall.member.service.MemberCollectSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
 * <p>
 * 会员收藏的商品 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/member/memberCollectSpu")
public class MemberCollectSpuController {

    @Autowired
    private MemberCollectSpuService memberCollectSpuService;

    /**
     * 获取所有数据
     */
    @GetMapping("/list")
    public R list() {
        List<MemberCollectSpuEntity> list = memberCollectSpuService.list();
        return R.ok().put("data", list);
    }

    /**
     * 根据ID获取数据
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MemberCollectSpuEntity entity = memberCollectSpuService.getById(id);
        return R.ok().put("data", entity);
    }

    /**
     * 保存数据
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberCollectSpuEntity memberCollectSpu) {
        boolean saved = memberCollectSpuService.save(memberCollectSpu);
        return saved ? R.ok() : R.error();
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    public R update(@RequestBody MemberCollectSpuEntity memberCollectSpu) {
        boolean updated = memberCollectSpuService.updateById(memberCollectSpu);
        return updated ? R.ok() : R.error();
    }

    /**
     * 删除数据
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        boolean removed = memberCollectSpuService.removeById(id);
        return removed ? R.ok() : R.error();
    }
}
