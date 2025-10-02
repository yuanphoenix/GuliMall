package com.atguigu.gulimall.product.app;

import com.atguigu.gulimall.product.entity.ProductAttrValueEntity;
import com.atguigu.gulimall.product.service.ProductAttrValueService;
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
 * spu属性值 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/productAttrValue")
public class ProductAttrValueController {

  @Autowired
  private ProductAttrValueService productAttrValueService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<ProductAttrValueEntity> list = productAttrValueService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    ProductAttrValueEntity entity = productAttrValueService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody ProductAttrValueEntity productAttrValue) {
    boolean saved = productAttrValueService.save(productAttrValue);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody ProductAttrValueEntity productAttrValue) {
    boolean updated = productAttrValueService.updateById(productAttrValue);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = productAttrValueService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
