package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrAttrgroupRelationService;
import com.atguigu.gulimall.product.service.AttrGroupService;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.gulimall.product.vo.AttrGroupResponseVo;
import com.atguigu.gulimall.product.vo.AttrResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.PageDTO;
import utils.R;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/attrgroup")
public class AttrGroupController {

  @Autowired
  private AttrService attrService;

  @Autowired
  private AttrGroupService attrGroupService;
  @Autowired
  private AttrAttrgroupRelationService attrAttrgroupRelationService;


  @GetMapping("/{attrgroupId}/noattr/relation")
  public R getNoAttrRelation(@PathVariable Long attrgroupId, @ModelAttribute PageDTO page) {
    IPage<AttrEntity> result = attrGroupService.getNoAttrRelationByGroupId(attrgroupId, page);
    return R.ok().put("page", result);
  }

  @GetMapping("/{catalogId}/withattr")
  public R getCatalogIdWithattr(@PathVariable Long catalogId) {
    List<AttrGroupResponseVo> data = attrGroupService.getCatalogIdWithattr(catalogId);
    return R.ok().put("data", data);
  }


  @GetMapping("/{attrgroupId}/attr/relation")
  public R listAttrRelationByGroupId(@PathVariable(name = "attrgroupId") Long attrgroupId) {
    List<AttrEntity> entities = attrService.listAttrWithRelationByGroupId(attrgroupId);
    return R.ok().put("data", entities);
  }

  @PostMapping("/attr/relation")
  public R addRelation(@RequestBody List<AttrAttrgroupRelationEntity> entityList) {
    boolean save = attrAttrgroupRelationService.checkAndSave(entityList);
    return save ? R.ok() : R.error();
  }

  @PostMapping("/attr/relation/delete")
  public R deleteRelation(@RequestBody List<AttrAttrgroupRelationEntity> entityList) {
    boolean delete = attrAttrgroupRelationService.removeRelationList(entityList);
    return delete ? R.ok() : R.error();
  }


  /**
   * 获取所有数据
   */
  @GetMapping("/list/{catalogId}")
  public R list(@PathVariable Long catalogId, @ModelAttribute PageDTO attrGroupQueryDTO) {

    IPage<AttrGroupEntity> attrGroupEntityIPage = attrGroupService.queryPage(attrGroupQueryDTO,
        catalogId);
    return R.ok().put("page", attrGroupEntityIPage);
  }

  /**
   * 获取所有数据
   */
  @GetMapping("/list/page")
  public R listPage(@ModelAttribute PageDTO attrGroupQueryDTO) {
    IPage<AttrGroupEntity> attrGroupEntityIPage = attrGroupService.queryPage(attrGroupQueryDTO);
    return R.ok().put("page", attrGroupEntityIPage);
  }


  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    AttrGroupEntity entity = attrGroupService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody AttrGroupEntity attrGroup) {
    boolean saved = attrGroupService.save(attrGroup);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody AttrGroupEntity attrGroup) {
    boolean updated = attrGroupService.updateById(attrGroup);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = attrGroupService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
