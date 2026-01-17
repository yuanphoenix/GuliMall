package com.atguigu.gulimall.gulimallcart.web;

import annotation.LoginUser;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.Cart;
import com.atguigu.gulimall.gulimallcart.vo.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.PathConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import to.MemberEntityVo;

@Slf4j
@Controller
public class CartWebController {

  private final CartService cartService;

  private final ObjectMapper objectMapper;

  public CartWebController(CartService cartService, ObjectMapper objectMapper) {
    this.cartService = cartService;
    this.objectMapper = objectMapper;
  }

  //    返回购物车列表界面
  @GetMapping("/cart.html")
  public String cartListPage(Model model, @LoginUser MemberEntityVo member) {
    Cart cart = cartService.getCart(member);
    model.addAttribute("cart", cart);
    return "cart";
  }

  @GetMapping("/addCart/{skuId}.html")
  public String addCart(@PathVariable Long skuId,
      @RequestParam(required = false, value = "num", defaultValue = "1") Integer num,
      RedirectAttributes redirectAttributes, @LoginUser MemberEntityVo member) {
    CartItem cartItem = cartService.addCart(member, skuId, num);
    redirectAttributes.addAttribute("skuId", skuId);
    return PathConstant.REDIRECT + "http://cart.gulimall.com/addCartSuccess.html";
  }

  @GetMapping("/addCartSuccess.html")
  public String addCartSuccess(@RequestParam("skuId") Long skuId, Model model,
      @LoginUser MemberEntityVo member) {
    CartItem cartItem = cartService.getCartItemBySkuId(member, skuId);
    if (cartItem == null) {
      return "success";
    }
    model.addAttribute("skuInfo", cartItem);
    model.addAttribute("skuNum", cartItem.getCount());
    return "success";
  }

}
