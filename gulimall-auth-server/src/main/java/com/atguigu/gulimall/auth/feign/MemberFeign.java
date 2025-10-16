package com.atguigu.gulimall.auth.feign;


import com.atguigu.gulimall.auth.vo.LoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import to.MemberTo;
import utils.R;

@FeignClient("gulimall-member")
public interface MemberFeign {

  @PostMapping("/member/member/save")
  R save(@RequestBody MemberTo member);


  @PostMapping("/member/member/checkLogin")
  R checkLogin(@RequestBody LoginVo loginVo);

}
