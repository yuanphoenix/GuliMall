package com.atguigu.gulimall.member.web;

import annotation.LoginUser;
import com.atguigu.gulimall.member.service.MemberService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import to.MemberEntityVo;
import to.order.OrderInfoTo;
import utils.R;

@Controller
public class WebController {

  private static final Logger log = LoggerFactory.getLogger(WebController.class);
  private final MemberService memberService;


  public WebController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/transfer.html")
  public String transfer(
      @RequestParam(value = "out_trade_no", required = false) String orderSn) {
    if (orderSn != null) {
//      orderFeign.changeOrderToPayed(new OrderPayedEvent(orderSn));
    }
//    return "orderList";
    return "redirect:http://member.gulimall.com/memberOrder.html";
  }


  @GetMapping("/memberOrder.html")
  public String memberOrder(Model model, @LoginUser MemberEntityVo memberEntityVo) {
    R list = memberService.test(memberEntityVo);
    var c = list.getPage(new TypeReference<IPage<OrderInfoTo>>() {
    });
    log.info(c.toString());
    model.addAttribute("data", c);
    return "orderList";
  }
}
