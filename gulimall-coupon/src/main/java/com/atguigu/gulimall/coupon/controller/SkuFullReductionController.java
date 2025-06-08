package com.atguigu.gulimall.coupon.controller;

import com.atguigu.gulimall.coupon.entity.SkuFullReductionEntity;
import com.atguigu.gulimall.coupon.service.SkuFullReductionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.SkuReducitionTo;
import utils.R;

/**
 * <p>
 * 商品满减信息 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/coupon/skuFullReduction")
public class SkuFullReductionController {

  @Autowired
  private SkuFullReductionService skuFullReductionService;


  @PostMapping("/saveInfoList")
  R saveSkuReduction(@RequestBody List<SkuReducitionTo> skuReducitionToList) {
    boolean b = skuFullReductionService.saveInfoList(skuReducitionToList);
    return b ? R.ok() : R.error();
  }

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<SkuFullReductionEntity> list = skuFullReductionService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    SkuFullReductionEntity entity = skuFullReductionService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody SkuFullReductionEntity skuFullReduction) {
    boolean saved = skuFullReductionService.save(skuFullReduction);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody SkuFullReductionEntity skuFullReduction) {
    boolean updated = skuFullReductionService.updateById(skuFullReduction);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = skuFullReductionService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
