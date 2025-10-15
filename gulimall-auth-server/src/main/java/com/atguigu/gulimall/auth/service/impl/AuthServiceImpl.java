package com.atguigu.gulimall.auth.service.impl;

import com.atguigu.gulimall.auth.feign.MemberFeign;
import com.atguigu.gulimall.auth.service.AuthService;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import to.MemberTo;
import utils.R;


/**
 * @author tifa
 */
@Service
public class AuthServiceImpl implements AuthService {

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
      return false;
    }
    String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
    stringRedisTemplate.opsForValue().set(phone, code, 5 * 60, TimeUnit.SECONDS);
    return true;
  }

  @Override
  public Boolean registMember(UserRegistVo userRegistVo) {
    MemberTo memberTo = new MemberTo();
    memberTo.setMobile(userRegistVo.getPhone());
    memberTo.setPassword(userRegistVo.getPassword());
    memberTo.setUsername(userRegistVo.getUserName());
    R save = memberFeign.save(memberTo);
    return save != null && Objects.equals(save.getCode(), R.ok().getCode());

  }
}
