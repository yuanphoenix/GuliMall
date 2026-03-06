package com.atguigu.gulimall.member.web;

import annotation.LoginUser;
import com.atguigu.gulimall.member.feign.OrderFeign;
import com.atguigu.gulimall.member.service.MemberService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import to.MemberEntityVo;
import to.order.OrderInfoTo;
import to.order.OrderPayedEvent;
import utils.R;

@Controller
public class WebController {

  private static final Logger log = LoggerFactory.getLogger(WebController.class);
  private final MemberService memberService;

  private final OrderFeign orderFeign;

  public WebController(MemberService memberService, OrderFeign orderFeign) {
    this.memberService = memberService;
    this.orderFeign = orderFeign;
  }

  @GetMapping("/transfer.html")
  public String transfer(
      @RequestParam(value = "out_trade_no", required = false) String orderSn) {
    if (orderSn != null) {
      //强制更新状态
      orderFeign.changeOrderToPayed(new OrderPayedEvent(orderSn));
    }
    return "redirect:http://member.gulimall.com/memberOrder.html?page=1&&limit=50";
  }


  @GetMapping("/memberOrder.html")
  public String memberOrder(Model model,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "limit", required = false) Integer limit,
      @LoginUser MemberEntityVo memberEntityVo) {

    OrderInfoTo orderInfoTo = new OrderInfoTo();
    orderInfoTo.setPage(page == null ? 1 : page);
    orderInfoTo.setLimit(limit == null ? 50 : limit);
    R list = memberService.getOrderList(memberEntityVo, orderInfoTo);
    var orderListResult = list.getPage(new TypeReference<Page<OrderInfoTo>>() {
    });
    model.addAttribute("data", orderListResult);
    return "orderList";
  }
}
