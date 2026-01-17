package com.atguigu.gulimall.order.service;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import com.baomidou.mybatisplus.extension.service.IService;
import to.MemberEntityVo;

/**
 * @author tifa
 * @description 针对表【oms_order(订单)】的数据库操作Service
 * @createDate 2025-05-08 21:18:35
 */
public interface OrderService extends IService<OrderEntity> {

  OrderConfirmVo confirmOrder(MemberEntityVo memberEntityVo);
}
