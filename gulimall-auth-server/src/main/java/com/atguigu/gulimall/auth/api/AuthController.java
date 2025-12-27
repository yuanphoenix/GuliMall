package com.atguigu.gulimall.auth.api;

import com.atguigu.gulimall.auth.service.AuthService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import utils.R;

/**
 * @author tifa
 */
@RestController
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }


  /**
   * 发送验证码
   *
   * @param phone 手机号
   * @return R
   */
  @GetMapping("/sms/sendCode")
  public R sendCode(@NotEmpty @RequestParam("phone") String phone) {
    return authService.sendCode(phone) ? R.ok() : R.error("验证码还在有效期内");
  }

}
