package com.atguigu.gulimall.ware.controller;

import com.atguigu.gulimall.ware.entity.WareInfoEntity;
import com.atguigu.gulimall.ware.service.WareInfoService;
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
import utils.PageUtils;
import utils.R;

/**
 * <p>
 * 仓库信息 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/ware/wareinfo")
public class WareInfoController {

  @Autowired
  private WareInfoService wareInfoService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list(@ModelAttribute PageDTO pageDTO) {
    IPage<WareInfoEntity> list = wareInfoService.pageWithCondition(pageDTO);
    return R.ok().put("page", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    WareInfoEntity entity = wareInfoService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody WareInfoEntity wareInfo) {
    boolean saved = wareInfoService.save(wareInfo);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody WareInfoEntity wareInfo) {
    boolean updated = wareInfoService.updateById(wareInfo);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete")
  public R delete(@RequestBody List<Long> ids) {
    boolean removed = wareInfoService.removeByIds(ids);
    return removed ? R.ok() : R.error();
  }
}
