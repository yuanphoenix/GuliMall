package com.atguigu.gulimall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {


  @GetMapping("/{target}.html")
  public String begin(@PathVariable String target) {
    return target;
  }
}
