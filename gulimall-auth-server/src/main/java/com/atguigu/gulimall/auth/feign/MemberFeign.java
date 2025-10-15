package com.atguigu.gulimall.auth.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import to.MemberTo;
import utils.R;

@FeignClient("gulimall-auth")
public interface MemberFeign {

  @PostMapping("/member/member/save")
  R save(@RequestBody MemberTo member);
}
