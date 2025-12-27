package com.atguigu.gulimall.gulimallcart;

import com.atguigu.gulimall.gulimallcart.vo.CartItem;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallCartApplicationTests {

  @Test
  void contextLoads() {
    CartItem cartItem = new CartItem();
    cartItem.setChecked(false);
    cartItem.setTitle("手机大");
  }

}
