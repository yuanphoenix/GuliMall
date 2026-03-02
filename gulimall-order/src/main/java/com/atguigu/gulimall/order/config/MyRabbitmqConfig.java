package com.atguigu.gulimall.order.config;

import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitmqConfig {

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }


  /**
   * 死信队列
   * <p>
   * 添加上Bean后，如果容器中没有，那么会在rabbitmq中自动创建。如果有，即使数据发生变化，也不会覆盖。
   *
   * @return
   */
  @Bean
  public Queue orderDelayQueue() {
//    	public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete,@Nullable Map<String, Object> arguments) {
    return new Queue("order.delay.queue", true, false, false,
        Map.of("x-dead-letter-exchange", "order-event-exchange",
            "x-dead-letter-routing-key", "order.release.order",
            "x-message-ttl", 60000));
  }

  @Bean
  public Queue orderReleaseQueue() {
    return new Queue("order.release.queue", true, false, false);
  }

  @Bean
  public Exchange orderEventExchange() {
//    	public TopicExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
    return new TopicExchange("order-event-exchange", false, false);
  }

  @Bean
  public Binding orderCreateOrder() {
    return new Binding("order.delay.queue", DestinationType.QUEUE, "order-event-exchange",
        "order.create.order", null);
  }

  @Bean
  public Binding orderReleaseOrder() {
    return new Binding("order.release.queue", DestinationType.QUEUE, "order-event-exchange",
        "order.release.order", null);
  }
}

