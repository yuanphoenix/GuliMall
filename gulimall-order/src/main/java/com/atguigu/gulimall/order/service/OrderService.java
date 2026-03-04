package com.atguigu.gulimall.order.service;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import com.atguigu.gulimall.order.vo.OrderSubmitVo;
import com.atguigu.gulimall.order.vo.SubmitOrderResponseVo;
import com.baomidou.mybatisplus.extension.service.IService;
import to.MemberEntityVo;

/**
 * @author tifa
 * @description 针对表【oms_order(订单)】的数据库操作Service
 * @createDate 2025-05-08 21:18:35
 */
public interface OrderService extends IService<OrderEntity> {

  OrderConfirmVo confirmOrder(MemberEntityVo memberEntityVo);

  /**
   * 订单从购物车提交后，这个方法可以用来查找。从redis购物车中获取哪些数据被check了。 然后从feign中获取最新价格，比较价格是否发生了变动。如果发生了变动那么就失败。
   * <p>
   * <p>
   * 从库存微服务中锁定库存。在订单为服务中插入数据。
   * <p>
   * 涉及redis的lua原子操作和分布式事务
   *
   * @param orderSubmitVo
   * @param memberEntityVo
   * @return
   */
  SubmitOrderResponseVo submit(OrderSubmitVo orderSubmitVo, MemberEntityVo memberEntityVo);

  /**
   * 验证订单并且准备下单
   *
   * @param orderSn
   * @param memberEntityVo
   * @return
   */
  OrderEntity preparePayInfo(String orderSn, MemberEntityVo memberEntityVo);


  /**
   * 使用支付宝支付
   *
   * @param orderSn
   * @param memberEntityVo
   * @return
   */
  String pay(String orderSn, MemberEntityVo memberEntityVo);


  /**
   * 根据订单号从支付宝验证是否支付成功
   * @param orderSn
   * @return
   */
  boolean isPay(String orderSn);
}
