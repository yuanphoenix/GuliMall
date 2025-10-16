package com.atguigu.gulimall.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {


  @GetMapping("/regist")
  public String register() {
    return "regist";
  }

}
