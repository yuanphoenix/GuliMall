package com.atguigu.gulimall.order.web;

import annotation.LoginUser;
import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import to.MemberEntityVo;
import utils.R;

@Slf4j
@Controller
public class WebController {

  private final RabbitTemplate rabbitTemplate;

  private final OrderService orderService;

  public WebController(OrderService orderService, RabbitTemplate rabbitTemplate) {
    this.orderService = orderService;
    this.rabbitTemplate = rabbitTemplate;
  }


  @GetMapping("/confirm.html")
  public String begin(@LoginUser MemberEntityVo memberEntityVo,
      Model model) {
    OrderConfirmVo confirmVo = orderService.confirmOrder(memberEntityVo);
    model.addAttribute("orderConfirmData", confirmVo);
    return "confirm";
  }

  @GetMapping("/list.html")
  public String list() {
    return "list";
  }


  @Deprecated
  @ResponseBody
  @GetMapping("/data")
  public R ddd(@LoginUser MemberEntityVo memberEntityVo) {
    OrderConfirmVo confirmVo = orderService.confirmOrder(memberEntityVo);
    return R.ok().put("data", confirmVo);
  }


  @GetMapping("/pay.html")
  public String payHtml(@RequestParam("orderSn") String orderSn, Model model,
      @LoginUser MemberEntityVo memberEntityVo) {

    OrderEntity orderEntity = orderService.preparePayInfo(orderSn, memberEntityVo);
    if (orderEntity == null) {
      throw new RuntimeException("付款错误");
    }
    model.addAttribute("orderSn", orderSn);
    model.addAttribute("orderEntity", orderEntity);
    return "pay";
  }


  @GetMapping("/test")
  public String createOrderTest() {
    //订单下单成功
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOrderSn(UUID.randomUUID().toString());
    //给mq发消息
    rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", orderEntity,
        new CorrelationData(UUID.randomUUID().toString()));
    return "";
  }
}
