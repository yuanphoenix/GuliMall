package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.entity.MemberCollectSubjectEntity;
import com.atguigu.gulimall.member.service.MemberCollectSubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.R;

/**
 * <p>
 * 会员收藏的专题活动 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/member/memberCollectSubject")
public class MemberCollectSubjectController {

  @Autowired
  private MemberCollectSubjectService memberCollectSubjectService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<MemberCollectSubjectEntity> list = memberCollectSubjectService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    MemberCollectSubjectEntity entity = memberCollectSubjectService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
    boolean saved = memberCollectSubjectService.save(memberCollectSubject);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody MemberCollectSubjectEntity memberCollectSubject) {
    boolean updated = memberCollectSubjectService.updateById(memberCollectSubject);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = memberCollectSubjectService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
