package com.atguigu.gulimall.order.config;

import com.atguigu.gulimall.order.interceptor.LoginInterceptor;
import com.atguigu.gulimall.order.resolver.LoginUserArgumentResolver;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final RabbitTemplate rabbitTemplate;
  private final LoginInterceptor loginInterceptor;
  private final LoginUserArgumentResolver loginUserArgumentResolver;
  public WebConfig(RabbitTemplate rabbitTemplate, LoginInterceptor loginInterceptor,
      LoginUserArgumentResolver loginUserArgumentResolver) {
    this.rabbitTemplate = rabbitTemplate;
    this.loginInterceptor = loginInterceptor;
    this.loginUserArgumentResolver = loginUserArgumentResolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/order/order/changeOrderToPayed/**"
            , "/order/order/list/**");
  }

  /**
   * 当这个微服务作为 生产者（Producer） 发送消息时，用来监听消息发送结果的。它跟消费者完全没关系。
   */
  @PostConstruct
  public void initRabbitTemplate() {

//    消息是否成功到达 Exchange
    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
      log.info("confirm{}", correlationData);
      if (ack) {
        log.info("ack为{}", true);
      } else {
        log.error("失败原因{}", cause);
      }
    });

//    当生产者的消息到达交换机却没有到达队列，才会回调这个
    rabbitTemplate.setReturnsCallback(
        returned -> log.error("消息到达交换机却没有到达队列{}", returned));
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginUserArgumentResolver);
  }
}
