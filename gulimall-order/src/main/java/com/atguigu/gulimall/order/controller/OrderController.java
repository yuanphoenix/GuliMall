package com.atguigu.gulimall.order.controller;

import annotation.LoginUser;
import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.OrderSubmitVo;
import com.atguigu.gulimall.order.vo.SubmitOrderResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.MemberEntityVo;
import to.order.OrderInfoTo;
import to.order.OrderPayedEvent;
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


  @PostMapping("/list")
  public R list(@RequestBody OrderInfoTo pageDTO) {
    IPage<OrderInfoTo> list = orderService.pageWithCondition(pageDTO);
    return R.ok().put("page", list);
  }


  @PostMapping("/submitOrder")
  public R submitOrder(@RequestBody OrderSubmitVo orderSubmitVo,
      @LoginUser MemberEntityVo memberEntityVo) {
    SubmitOrderResponseVo res = orderService.submit(orderSubmitVo, memberEntityVo);
    return R.ok().put("data", res);
  }


  @PostMapping("/changeOrderToPayed")
  public R changeOrderToPayed(@RequestBody OrderPayedEvent orderPayedEvent) {
    orderService.changeOrderStateToPayed(orderPayedEvent.getOrderSn());
    return R.ok();
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
