package com.atguigu.gulimall.ware.controller;

import com.atguigu.gulimall.ware.entity.WareOrderTaskEntity;
import com.atguigu.gulimall.ware.service.WareOrderTaskService;
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
 * 库存工作单 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/ware/wareOrderTask")
public class WareOrderTaskController {

  @Autowired
  private WareOrderTaskService wareOrderTaskService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<WareOrderTaskEntity> list = wareOrderTaskService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    WareOrderTaskEntity entity = wareOrderTaskService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody WareOrderTaskEntity wareOrderTask) {
    boolean saved = wareOrderTaskService.save(wareOrderTask);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody WareOrderTaskEntity wareOrderTask) {
    boolean updated = wareOrderTaskService.updateById(wareOrderTask);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = wareOrderTaskService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
