package com.atguigu.gulimall.auth.web;

import com.atguigu.gulimall.auth.service.AuthService;
import com.atguigu.gulimall.auth.vo.LoginVo;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import constant.LoginConstant;
import constant.PathConstant;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import to.MemberEntityVo;

@Controller
public class IndexController {

  private final AuthService authService;
  private final StringRedisTemplate redisTemplate;

  public IndexController(AuthService authService,StringRedisTemplate redisTemplate) {
    this.authService = authService;
    this.redisTemplate = redisTemplate;
  }

  @GetMapping("/regist")
  public String register() {
    return "regist";
  }


  @PostMapping("/regist")
  public String register(@Valid UserRegistVo vo) {
    //调用其他微服务接口做注册
    return authService.registMember(vo) ? "redirect:http://auth.gulimall.com"
        : "redirect:http://auth.gulimall.com/regist";
  }


  @PostMapping("/login")
  public String login(@Valid LoginVo loginVo, HttpSession session) {
    MemberEntityVo login = authService.login(loginVo);
    if (login != null) {
      session.setAttribute(LoginConstant.LOGIN.getValue(), login);
      String backurl = redisTemplate.opsForValue()
          .get(LoginConstant.BACK_URL.getValue() + loginVo.getBackurl());
      if (loginVo.getBackurl() != null && backurl != null) {
        redisTemplate.delete(LoginConstant.BACK_URL.getValue() + loginVo.getBackurl());
        return PathConstant.REDIRECT + backurl;
      }

      return PathConstant.REDIRECT + "http://gulimall.com";
    }
    return PathConstant.REDIRECT + "http://auth.gulimall.com";
  }


}
