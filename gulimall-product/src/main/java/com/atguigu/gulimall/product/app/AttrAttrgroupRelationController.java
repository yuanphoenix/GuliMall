package com.atguigu.gulimall.product.app;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.service.AttrAttrgroupRelationService;
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
 * 属性&属性分组关联 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/attrAttrgroupRelation")
public class AttrAttrgroupRelationController {

  @Autowired
  private AttrAttrgroupRelationService attrAttrgroupRelationService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<AttrAttrgroupRelationEntity> list = attrAttrgroupRelationService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    AttrAttrgroupRelationEntity entity = attrAttrgroupRelationService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation) {
    boolean saved = attrAttrgroupRelationService.save(attrAttrgroupRelation);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelation) {
    boolean updated = attrAttrgroupRelationService.updateById(attrAttrgroupRelation);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = attrAttrgroupRelationService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
