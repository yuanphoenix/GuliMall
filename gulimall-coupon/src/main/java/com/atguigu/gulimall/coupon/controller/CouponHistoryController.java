package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.CouponHistoryEntity;
import com.atguigu.gulimall.coupon.service.CouponHistoryService;
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
 * 优惠券领取历史记录 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/couponHistory")
public class CouponHistoryController {

  @Autowired
  private CouponHistoryService couponHistoryService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<CouponHistoryEntity> list = couponHistoryService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    CouponHistoryEntity entity = couponHistoryService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody CouponHistoryEntity couponHistory) {
    boolean saved = couponHistoryService.save(couponHistory);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody CouponHistoryEntity couponHistory) {
    boolean updated = couponHistoryService.updateById(couponHistory);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = couponHistoryService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
