package com.atguigu.gulimall.gulimallcart.api;

import annotation.LoginUser;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.Cart;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.MemberEntityVo;
import to.cart.CartItem;
import utils.R;

@Slf4j
@RestController
@RequestMapping("/api")
public class CartApiController {

  @Autowired
  private CartService cartService;

  @PostMapping("/changeCart")
  public R checkCart(@RequestBody CartItem cartItem, @LoginUser MemberEntityVo memberEntityVo) {
    Boolean result = cartService.changeCart(cartItem, memberEntityVo);
    return Boolean.TRUE.equals(result) ? R.ok() : R.error();
  }

  @PostMapping("/deleteBySkuIds")
  public R deleteBySkuIds(@RequestBody List<CartItem> cartItemList,
      @LoginUser MemberEntityVo memberEntityVo) {
    Boolean result = cartService.deleteByItem(cartItemList, memberEntityVo);
    return Boolean.TRUE.equals(result) ? R.ok() : R.error();
  }


  @GetMapping("/getCartItems/{memberId}")
  public List<CartItem> getCartItems(@PathVariable("memberId") Long memberId) {
    MemberEntityVo memberEntityVo = new MemberEntityVo();
    memberEntityVo.setId(memberId);
    Cart cart = cartService.getCart(memberEntityVo);
    return cart.getCartItemList();
  }


}
