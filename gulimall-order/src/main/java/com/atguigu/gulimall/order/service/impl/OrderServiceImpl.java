package com.atguigu.gulimall.order.service.impl;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.mapper.OrderMapper;
import com.atguigu.gulimall.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.Channel;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author tifa
 * @description 针对表【oms_order(订单)】的数据库操作Service实现
 * @createDate 2025-05-08 21:18:35
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity>
    implements OrderService {

  @Autowired
  private RabbitTemplate rabbitTemplate;


  @RabbitListener(queues = {"hello"})
  void listen(Message message, @Payload OrderEntity orderEntity, Channel channel)
      throws IOException {
    System.out.println("消费者" + orderEntity);
    long deliveryTag = message.getMessageProperties().getDeliveryTag();

    channel.basicAck(deliveryTag, false);
  }


  /**
   * 确认回调
   */
  @PostConstruct
  public void initRabbitTemplate() {
    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
      System.out.println("confirm..." + correlationData);
      System.out.println("ack..." + ack);
      System.out.println("失败原因..." + cause);
    });

//    当生产者的消息没有到达队列，才会回调这个
    rabbitTemplate.setReturnsCallback(new ReturnsCallback() {
      @Override
      public void returnedMessage(ReturnedMessage returned) {
        System.out.println(returned);
      }
    });

  }

}




