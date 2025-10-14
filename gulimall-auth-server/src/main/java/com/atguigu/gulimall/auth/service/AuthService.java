package com.atguigu.gulimall.auth.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface AuthService {

  Boolean sendCode(@RequestParam("phone") String phone);


}
