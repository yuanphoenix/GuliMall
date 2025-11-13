package com.atguigu.gulimall.auth.service;

import com.atguigu.gulimall.auth.vo.LoginVo;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import org.springframework.web.bind.annotation.RequestParam;
import to.MemberEntityVo;

public interface AuthService {

  Boolean sendCode(@RequestParam("phone") String phone);

  Boolean registMember(UserRegistVo memberTo);


  MemberEntityVo login(LoginVo loginVo);
}
