/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.controller;


import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP登录授权
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
public class AppLoginController {

  @Autowired
  private UserService userService;
  @Autowired
  private JwtUtils jwtUtils;

  /**
   * 登录
   */
  @PostMapping("login")
  public R login(@RequestBody LoginForm form) {
    //表单校验
    ValidatorUtils.validateEntity(form);

    //用户登录
    long userId = userService.login(form);

    //生成token
    String token = jwtUtils.generateToken(userId);

    Map<String, Object> map = new HashMap<>();
    map.put("token", token);
    map.put("expire", jwtUtils.getExpire());

    return R.ok(map);
  }

}
