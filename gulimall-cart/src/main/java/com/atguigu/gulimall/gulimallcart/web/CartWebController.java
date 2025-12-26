package com.atguigu.gulimall.gulimallcart.web;

import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.SkuInfoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.LoginConstant;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import to.MemberEntityVo;

@Slf4j
@Controller
public class CartWebController {

  @Autowired
  private CartService cartService;

  @Autowired
  private ObjectMapper objectMapper;

  //    返回购物车列表界面
  @GetMapping("/cart.html")
  public String cartListPage(HttpSession session) {
    Object attribute = session.getAttribute(LoginConstant.LOGIN.getValue());
    if (attribute == null) {
      //如果没有登录，让用户去登录
      return "redirect:http://auth.gulimall.com";
    }
    return "cart";
  }

  @GetMapping("/addCart/{skuId}.html")
  public String addCart(@PathVariable Long skuId,
      @RequestParam(required = false, value = "num", defaultValue = "1") Long num,
      HttpSession session, Model model) {

    Object attribute = session.getAttribute(LoginConstant.LOGIN.getValue());
    if (attribute == null) {
      return "redirect:http://auth.gulimall.com";
    }
    //添加到购物车
    MemberEntityVo member = objectMapper.convertValue(attribute, MemberEntityVo.class);
    SkuInfoEntity skuEsModel = cartService.addCart(member, skuId, num);
    log.info(skuEsModel.toString());
    model.addAttribute("skuInfo", skuEsModel);
    model.addAttribute("skuNum", num);
    return "success";

  }
}
