package com.atguigu.gulimall.auth.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


  @GetMapping("/regist")
  public String register() {
    return "regist";
  }

}
