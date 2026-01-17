package com.atguigu.gulimall.member.service;

import com.atguigu.gulimall.member.entity.MemberReceiveAddressEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【ums_member_receive_address(会员收货地址)】的数据库操作Service
 * @createDate 2025-05-08 21:36:52
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

  List<MemberReceiveAddressEntity> selectByMemberId(Long memberId);
}
