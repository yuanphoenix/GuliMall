package com.atguigu.gulimall.order.controller;

import annotation.LoginUser;
import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.OrderSubmitVo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.MemberEntityVo;
import utils.R;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@Slf4j
@RestController
@RequestMapping("/order/order")
public class OrderController {

  @Autowired
  private OrderService orderService;


  @PostMapping("/submitOrder")
  public R submitOrder(@RequestBody OrderSubmitVo orderSubmitVo,
      @LoginUser MemberEntityVo memberEntityVo) {
    Boolean res = orderService.submit(orderSubmitVo, memberEntityVo);
    return Boolean.TRUE.equals(res) ? R.ok() : R.error("订单提交失败");
  }


  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<OrderEntity> list = orderService.list();

    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    OrderEntity entity = orderService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody OrderEntity order) {
    boolean saved = orderService.save(order);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody OrderEntity order) {
    boolean updated = orderService.updateById(order);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = orderService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
