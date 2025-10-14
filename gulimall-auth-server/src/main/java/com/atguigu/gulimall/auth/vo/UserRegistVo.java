package com.atguigu.gulimall.auth.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author tifa
 */
@Data
public class UserRegistVo {

  @NotEmpty(message = "用户名必须提交")
  private String userName;
  @NotEmpty(message = "手机号必须填写")
  private String phone;

  @NotEmpty(message = "密码必须填写")
  @Length(min = 6, max = 20)
  private String password;

  @NotEmpty(message = "验证码必须填写")
  private String code;
}
