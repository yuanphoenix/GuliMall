package com.atguigu.gulimall.product.controller;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.gulimall.product.vo.AttrResponseVo;
import com.atguigu.gulimall.product.vo.AttrVo;
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
 * 商品属性 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/attr")
public class AttrController {

  @Autowired
  private AttrService attrService;


  @GetMapping("/sale/list/{catalogId}")
  public R saleList(@PathVariable("catalogId") Long catalogId, @ModelAttribute PageDTO pageDTO) {
    IPage<AttrResponseVo> attrEntityIPage = attrService.getSaleList(catalogId, pageDTO);
    return R.ok().put("data", attrEntityIPage);
  }


  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<AttrEntity> list = attrService.list();
    return R.ok().put("data", list);
  }

  @GetMapping("/base/list/{catalogId}")
  public R baseList(@PathVariable Long catalogId, @ModelAttribute PageDTO pageDTO) {
    IPage<AttrResponseVo> attrEntityIPage = attrService.getBaseList(catalogId, pageDTO);
    return R.ok().put("data", attrEntityIPage);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    AttrResponseVo entity = attrService.getAttrResponseVo(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody AttrVo attr) {
    boolean saved = attrService.saveVo(attr);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody AttrVo attr) {
    boolean updated = attrService.updateAttrVo(attr);
    return updated ? R.ok() : R.error();
  }


  @PostMapping("/delete")
  public R batchDelete(@RequestBody List<Long> ids) {
    boolean removed = attrService.removeAttrAndRelationByIds(ids);
    return removed ? R.ok() : R.error();
  }
}
