package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.atguigu.gulimall.product.service.SkuSaleAttrValueService;
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
 * sku销售属性&值 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/skuSaleAttrValue")
public class SkuSaleAttrValueController {

  @Autowired
  private SkuSaleAttrValueService skuSaleAttrValueService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<SkuSaleAttrValueEntity> list = skuSaleAttrValueService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    SkuSaleAttrValueEntity entity = skuSaleAttrValueService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody SkuSaleAttrValueEntity skuSaleAttrValue) {
    boolean saved = skuSaleAttrValueService.save(skuSaleAttrValue);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody SkuSaleAttrValueEntity skuSaleAttrValue) {
    boolean updated = skuSaleAttrValueService.updateById(skuSaleAttrValue);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = skuSaleAttrValueService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
