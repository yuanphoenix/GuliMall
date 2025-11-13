package com.atguigu.gulimall.member.controller;

import com.atguigu.gulimall.member.feign.CouponFeignService;
import com.atguigu.gulimall.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.gulimall.member.entity.MemberEntity;
import to.MemberEntityVo;
import utils.R;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/member/member")
public class MemberController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private CouponFeignService couponFeignService;

  @Autowired
  private ObjectMapper objectMapper;


  @GetMapping("/test")
  public R test() {
    R list = couponFeignService.list();
    return R.ok().put("list", list);
  }

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<MemberEntity> list = memberService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    MemberEntity entity = memberService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@Valid @RequestBody MemberEntity member) {
    boolean saved = memberService.saveMember(member);
    return saved ? R.ok() : R.error();
  }


  @PostMapping("/checkLogin")
  public R checkLogin(@RequestBody MemberEntity member) {
    MemberEntityVo memberEntity = memberService.checkLogin(member);
    System.out.println("打印一下mapper"+objectMapper);
    return memberEntity != null ? R.ok().put("data", memberEntity) : R.error();
  }


  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody MemberEntity member) {
    boolean updated = memberService.updateById(member);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = memberService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
