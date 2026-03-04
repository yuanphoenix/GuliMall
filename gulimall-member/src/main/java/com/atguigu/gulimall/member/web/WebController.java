package com.atguigu.gulimall.member.web;

import com.atguigu.gulimall.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

  private static final Logger log = LoggerFactory.getLogger(WebController.class);
  @Autowired
  private MemberService memberService;

  @GetMapping("/memberOrder.html")
  public String memberOrder(
      @RequestParam(value = "out_trade_no", required = false) String orderSn) {
    log.info("out_trade_no{}", orderSn);
    memberService.sendPayed(orderSn);

    return "orderList";
  }
}
