package com.atguigu.gulimall.gulimallcart.web;

import constant.LoginConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartWebController {
    @GetMapping("/cart.html")
    public String cartListPage(HttpSession session) {
        Object attribute = session.getAttribute(LoginConstant.LOGIN.getValue());
        if (attribute == null) {
            //如果没有登录，让用户去登录
            return "redirect:http://auth.gulimall.com";
        }
        return "cart";
    }
}
