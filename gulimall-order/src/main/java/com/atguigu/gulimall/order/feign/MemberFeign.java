package com.atguigu.gulimall.order.feign;


import com.atguigu.gulimall.order.vo.MemberAddressVo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gulimall-member")
public interface MemberFeign {

  @GetMapping("/member/memberReceiveAddress/{memberId}")
  List<MemberAddressVo> getMemberByMemberId(@PathVariable Long memberId);
}
