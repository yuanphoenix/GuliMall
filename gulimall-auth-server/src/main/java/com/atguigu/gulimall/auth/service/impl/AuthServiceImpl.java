package com.atguigu.gulimall.auth.service.impl;

import com.atguigu.gulimall.auth.feign.MemberFeign;
import com.atguigu.gulimall.auth.service.AuthService;
import com.atguigu.gulimall.auth.vo.LoginVo;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BizCodeEnum;
import exception.BizException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import to.MemberEntityVo;
import to.MemberTo;
import utils.R;


/**
 * @author tifa
 */
@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private ObjectMapper objectMapper;  // Spring Boot 注入的 ObjectMapper

  private final MemberFeign memberFeign;

  private final StringRedisTemplate stringRedisTemplate;

  public AuthServiceImpl(StringRedisTemplate stringRedisTemplate, MemberFeign memberFeign) {
    this.stringRedisTemplate = stringRedisTemplate;
    this.memberFeign = memberFeign;
  }

  @Override
  public Boolean sendCode(String phone) {
    String s = stringRedisTemplate.opsForValue().get(phone);
    if (StringUtils.hasText(s)) {
      throw new BizException(BizCodeEnum.CODE_TIME_EXCEPTION);
    }
    String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
    stringRedisTemplate.opsForValue().set(phone, code, 5 * 60, TimeUnit.SECONDS);
    return true;
  }

  @Override
  public Boolean registMember(UserRegistVo userRegistVo) {
    String code = userRegistVo.getCode();
    String phone = userRegistVo.getPhone();
    if (!org.apache.commons.lang3.StringUtils.equals(code,
        stringRedisTemplate.opsForValue().get(phone))) {
      throw new BizException(BizCodeEnum.CODE_EXCEPTION);
    }
    MemberTo memberTo = new MemberTo();
    memberTo.setMobile(userRegistVo.getPhone());
    memberTo.setPassword(userRegistVo.getPassword());
    memberTo.setUsername(userRegistVo.getUserName());

    R save = memberFeign.save(memberTo);
    if (save != null && save.getCode() == 0) {
      stringRedisTemplate.delete(phone);
      return true;
    }
    throw new BizException(BizCodeEnum.AUTH_SAVE_EXCEPTION);
  }

  @Override
  public MemberEntityVo login(LoginVo loginVo) {
    R r = memberFeign.checkLogin(loginVo);
    if (r.getCode() == 0) {
      MemberEntityVo data = objectMapper.convertValue(r.get("data"), MemberEntityVo.class);
      return data;
    }
    return null;
  }

}
