package com.atguigu.gulimall.auth.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


  @GetMapping("/regist")
  public String register() {
    return "regist";
  }

  @GetMapping("/")
  public String index(HttpServletRequest request, Model model) {
    Object login = request.getSession().getAttribute("login");
    if (login != null) {
      return "redirect:http://gulimall.com";
    }
    return "index";
  }
}
