package com.atguigu.gulimall.order.controller;

import com.atguigu.gulimall.order.entity.OrderOperateHistoryEntity;
import com.atguigu.gulimall.order.service.OrderOperateHistoryService;
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
 * 订单操作历史记录 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/order/orderOperateHistory")
public class OrderOperateHistoryController {

  @Autowired
  private OrderOperateHistoryService orderOperateHistoryService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<OrderOperateHistoryEntity> list = orderOperateHistoryService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    OrderOperateHistoryEntity entity = orderOperateHistoryService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody OrderOperateHistoryEntity orderOperateHistory) {
    boolean saved = orderOperateHistoryService.save(orderOperateHistory);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody OrderOperateHistoryEntity orderOperateHistory) {
    boolean updated = orderOperateHistoryService.updateById(orderOperateHistory);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = orderOperateHistoryService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
