package com.atguigu.gulimall.order;


import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallOrderApplicationTests {

  @Autowired
  private AmqpAdmin amqpAdmin;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Test
  void createExchange() {
    DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
    amqpAdmin.declareExchange(directExchange);
  }

  @Test
  void createQueue() {
    amqpAdmin.declareQueue(new Queue("hello"));
  }

  @Test
  void createbind() {
    var bind = new Binding("hello", DestinationType.QUEUE, "hello-java-exchange", "hello-java",
        null);
    amqpAdmin.declareBinding(bind);
  }

  @Test
  void sendMsg() {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setId(12343L);

    rabbitTemplate.convertAndSend("hello-java-exchange", "hello-java",
        orderEntity,
        new CorrelationData(
            UUID.randomUUID().toString()));
    System.out.println("已经发送");
  }
}
