package com.atguigu.gulimall.auth.service;

import com.atguigu.gulimall.auth.vo.LoginVo;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import org.springframework.web.bind.annotation.RequestParam;
import to.MemberEntityVo;

public interface AuthService {

  Boolean sendCode(@RequestParam("phone") String phone);

  Boolean registMember(UserRegistVo memberTo);


  /**
   * 用户登录接口
   *
   * @param loginVo
   * @return
   */
  MemberEntityVo login(LoginVo loginVo);
}
