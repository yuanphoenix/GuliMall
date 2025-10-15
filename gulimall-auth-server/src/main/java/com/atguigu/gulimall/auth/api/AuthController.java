package com.atguigu.gulimall.auth.api;

import com.atguigu.gulimall.auth.service.AuthService;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.R;

/**
 * @author tifa
 */
@Controller
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
  @ResponseBody
  @GetMapping("/sms/sendCode")
  public R sendCode(@NotEmpty @RequestParam("phone") String phone) {
    return authService.sendCode(phone) ? R.ok() : R.error("验证码还有有效期内");
  }


  @PostMapping("/regist")
  public String register(@Valid UserRegistVo vo) {

    //调用其他微服务接口做注册
    Boolean b = authService.registMember(vo);
    return b ? "redirect:http://auth.gulimall.com" : "redirect:http://auth.gulimall.com/regist";
  }

}
