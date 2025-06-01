/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.controller;


import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
public class AppTestController {

  @Login
  @GetMapping("userInfo")
  public R userInfo(@LoginUser UserEntity user) {
    return R.ok().put("user", user);
  }

  @Login
  @GetMapping("userId")
  public R userInfo(@RequestAttribute("userId") Integer userId) {
    return R.ok().put("userId", userId);
  }

  @GetMapping("notToken")
  public R notToken() {
    return R.ok().put("msg", "无需token也能访问。。。");
  }

}
