/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.form;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class RegisterForm {

  @NotBlank(message = "手机号不能为空")
  private String mobile;

  @NotBlank(message = "密码不能为空")
  private String password;

}
