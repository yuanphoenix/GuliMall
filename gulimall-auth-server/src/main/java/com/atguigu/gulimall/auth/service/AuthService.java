package com.atguigu.gulimall.auth.service;

import com.atguigu.gulimall.auth.vo.UserRegistVo;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthService {

  Boolean sendCode(@RequestParam("phone") String phone);

  Boolean registMember(UserRegistVo memberTo);
}
