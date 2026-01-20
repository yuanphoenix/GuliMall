package com.atguigu.gulimall.order.web;

import annotation.LoginUser;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import to.MemberEntityVo;
import utils.R;

@Controller
public class WebController {

  @Autowired
  private OrderService orderService;


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


  @ResponseBody
  @GetMapping("/data")
  public R ddd(@LoginUser MemberEntityVo memberEntityVo) {
    OrderConfirmVo confirmVo = orderService.confirmOrder(memberEntityVo);
    return R.ok().put("data", confirmVo);
  }

  @GetMapping("/pay.html")
  public String payhtml() {
    return "pay";
  }
}
