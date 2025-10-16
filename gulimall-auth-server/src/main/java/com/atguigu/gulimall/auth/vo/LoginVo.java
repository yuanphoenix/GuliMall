package com.atguigu.gulimall.auth.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author tifa
 */
@Data
public class LoginVo {

  @NotEmpty
  private String username;
  @NotEmpty
  private String password;
}
