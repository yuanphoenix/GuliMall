package com.atguigu.gulimall.ware.config;

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
public class MyRabbitMqConfig {

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  /**
   *
   * @return 死信队列
   */
  @Bean
  public Queue stockDelayQueue() {
//  12分钟的延时队列，到时候根据情况自动解锁库存
    return new Queue("stock-delay-queue", true, false, false,
        Map.of("x-dead-letter-exchange", "stock-event-exchange",
            "x-dead-letter-routing-key", "stock.release.stock",
            "x-message-ttl", 10000 * 12));
  }


  @Bean
  public Queue stockReleaseQueue() {
    return new Queue("stock-release-queue", true, false, false);
  }


  @Bean
  public Queue stockMinusQueue() {
    return new Queue("stock-minus-queue", true, false, false);
  }



  @Bean
  public Exchange stockEventExchange() {
    return new TopicExchange("stock-event-exchange", true, false);
  }

  @Bean
  public Binding stockCreateBind() {
    return new Binding("stock-delay-queue", DestinationType.QUEUE, "stock-event-exchange"
        , "stock.delay.stock", null);
  }

  @Bean
  public Binding stockReleaseBind() {
    return new Binding("stock-release-queue", DestinationType.QUEUE, "stock-event-exchange"
        , "stock.release.stock", null);
  }

  @Bean
  public Binding stockMinusBind() {
    return new Binding("stock-minus-queue", DestinationType.QUEUE, "stock-event-exchange"
        , "stock.minus.stock", null);
  }

}
