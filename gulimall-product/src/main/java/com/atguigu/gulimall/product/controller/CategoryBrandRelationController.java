package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.R;

/**
 * <p>
 * 品牌分类关联 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/categorybrandrelation")
public class CategoryBrandRelationController {

  @Autowired
  private CategoryBrandRelationService categoryBrandRelationService;



  /**
   * 获取所有数据
   */
  @GetMapping("/brands/list")
  public R list(@ModelAttribute CategoryEntity categoryEntity) {
    List<CategoryBrandRelationEntity> list = categoryBrandRelationService.listByCategoryEntity(categoryEntity);
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    CategoryBrandRelationEntity entity = categoryBrandRelationService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    boolean saved = categoryBrandRelationService.saveDetail(categoryBrandRelation);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    boolean updated = categoryBrandRelationService.updateById(categoryBrandRelation);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = categoryBrandRelationService.removeById(id);
    return removed ? R.ok() : R.error();
  }


  /**
   * 根据品牌ID查询各个分类
   *
   * @param brandId 品牌的ID
   * @return
   */
  @GetMapping("/catalog/list")
  public R catalogList(@RequestParam("brandId") Long brandId) {
    List<CategoryBrandRelationEntity> categoryBrandRelationEntityList = categoryBrandRelationService.list(
        new LambdaQueryWrapper<CategoryBrandRelationEntity>().eq(
            CategoryBrandRelationEntity::getBrandId, brandId));
    return R.ok().put("data", categoryBrandRelationEntityList);
  }

}
