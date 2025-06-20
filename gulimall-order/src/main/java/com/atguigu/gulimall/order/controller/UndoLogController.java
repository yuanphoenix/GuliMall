package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.UndoLogEntity;
import com.atguigu.gulimall.order.service.UndoLogService;
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
 * 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/order/undoLog")
public class UndoLogController {

  @Autowired
  private UndoLogService undoLogService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<UndoLogEntity> list = undoLogService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    UndoLogEntity entity = undoLogService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody UndoLogEntity undoLog) {
    boolean saved = undoLogService.save(undoLog);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody UndoLogEntity undoLog) {
    boolean updated = undoLogService.updateById(undoLog);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = undoLogService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
