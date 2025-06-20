package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.TreeVoRequest;
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
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  /**
   * 获取所有数据
   * <p>
   * 查出所以分类以及子分类，并且以树形结构组装起来。
   */
  @GetMapping("/list/tree")
  public R list() {
    List<CategoryEntity> list = categoryService.listWithTree();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    CategoryEntity entity = categoryService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody CategoryEntity category) {
    boolean saved = categoryService.addNew(category);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody CategoryEntity category) {
    boolean updated = categoryService.updateById(category);
    return updated ? R.ok() : R.error();
  }


  @PostMapping("/sort")
  public R sort(@RequestBody TreeVoRequest treeVoRequest) {
    boolean sort = categoryService.sort(treeVoRequest);
    return sort ? R.ok() : R.error();

  }


  /**
   * 删除数据
   */
  @PostMapping("/delete")
  public R delete(@RequestBody CategoryEntity category) {
    boolean removed = categoryService.checkAndRemove(category);
    return removed ? R.ok() : R.error();
  }

  @PostMapping("/batchDelete")
  public R batchDelete(@RequestBody List<CategoryEntity> categoryEntityList) {

    return categoryService.removeBatchByEntities(categoryEntityList) ? R.ok() : R.error("删除失败");
  }


}
