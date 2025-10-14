package com.atguigu.gulimall.auth.service.impl;

import com.atguigu.gulimall.auth.service.AuthService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author tifa
 */
@Service
public class AuthServiceImpl implements AuthService {

  private final StringRedisTemplate stringRedisTemplate;

  public AuthServiceImpl(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

  @Override
  public Boolean sendCode(String phone) {
    String s = stringRedisTemplate.opsForValue().get(phone);
    if (StringUtils.hasText(s)) {
      return false;
    }
    String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
    stringRedisTemplate.opsForValue().set(phone, code, 5 * 60, TimeUnit.SECONDS);
    return true;
  }
}
