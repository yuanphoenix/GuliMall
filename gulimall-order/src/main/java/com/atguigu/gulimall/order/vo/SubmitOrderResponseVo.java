package com.atguigu.gulimall.order.vo;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.entity.OrderItemEntity;
import java.util.List;
import lombok.Data;

/**
 * 提交订单的返回类型
 */
@Data
public class SubmitOrderResponseVo {

  private Integer code;
  private OrderEntity orderEntity;
  private List<OrderItemEntity> orderItemEntityList;
}
