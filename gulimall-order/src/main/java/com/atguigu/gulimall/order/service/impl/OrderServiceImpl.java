package com.atguigu.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author tifa
* @description 针对表【oms_order(订单)】的数据库操作Service实现
* @createDate 2025-05-08 21:18:35
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity>
    implements OrderService{

}




