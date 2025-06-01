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
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.form.RegisterForm;
import io.renren.modules.app.service.UserService;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
public class AppRegisterController {

  @Autowired
  private UserService userService;

  @PostMapping("register")
  public R register(@RequestBody RegisterForm form) {
    //表单校验
    ValidatorUtils.validateEntity(form);

    UserEntity user = new UserEntity();
    user.setMobile(form.getMobile());
    user.setUsername(form.getMobile());
    user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
    user.setCreateTime(new Date());
    userService.save(user);

    return R.ok();
  }
}
