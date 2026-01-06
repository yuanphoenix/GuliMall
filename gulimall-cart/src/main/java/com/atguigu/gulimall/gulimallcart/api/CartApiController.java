package com.atguigu.gulimall.gulimallcart.api;

import com.atguigu.gulimall.gulimallcart.annotation.LoginUser;
import com.atguigu.gulimall.gulimallcart.service.CartService;
import com.atguigu.gulimall.gulimallcart.vo.CartItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import to.MemberEntityVo;
import utils.R;

@RestController
public class CartApiController {

  @Autowired
  private CartService cartService;

  @PostMapping("/changeCart")
  public R checkCart(@RequestBody CartItem cartItem, @LoginUser MemberEntityVo memberEntityVo) {
    Boolean result = cartService.changeCart(cartItem, memberEntityVo);
    return Boolean.TRUE.equals(result) ? R.ok() : R.error();
  }

  @PostMapping("/deleteBySkuIds")
  public R deleteBySkuIds(@RequestBody List<CartItem> cartItemList ,@LoginUser MemberEntityVo memberEntityVo) {
    Boolean result = cartService.deleteByItem(cartItemList,memberEntityVo);
    return Boolean.TRUE.equals(result) ? R.ok() : R.error();
  }

}
