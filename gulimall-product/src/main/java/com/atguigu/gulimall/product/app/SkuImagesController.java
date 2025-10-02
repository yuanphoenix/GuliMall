package com.atguigu.gulimall.product.app;

import com.atguigu.gulimall.product.entity.SkuImagesEntity;
import com.atguigu.gulimall.product.service.SkuImagesService;
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
 * sku图片 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/skuImages")
public class SkuImagesController {

  @Autowired
  private SkuImagesService skuImagesService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<SkuImagesEntity> list = skuImagesService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    SkuImagesEntity entity = skuImagesService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody SkuImagesEntity skuImages) {
    boolean saved = skuImagesService.save(skuImages);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody SkuImagesEntity skuImages) {
    boolean updated = skuImagesService.updateById(skuImages);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = skuImagesService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
