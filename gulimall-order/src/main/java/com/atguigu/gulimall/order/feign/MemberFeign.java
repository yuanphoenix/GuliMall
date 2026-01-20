package com.atguigu.gulimall.order.feign;


import com.atguigu.gulimall.order.vo.MemberAddressVo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import utils.R;

@FeignClient("gulimall-member")
public interface MemberFeign {

  @GetMapping("/member/memberReceiveAddress/{memberId}")
  List<MemberAddressVo> getMemberByMemberId(@PathVariable Long memberId);


  @GetMapping("/member/memberReceiveAddress/info/{id}")
  R getAddressById(@PathVariable(name = "id") Long addrId);
}
