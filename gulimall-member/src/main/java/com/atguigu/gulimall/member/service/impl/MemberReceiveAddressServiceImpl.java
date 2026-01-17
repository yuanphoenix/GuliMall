package com.atguigu.gulimall.member.service.impl;

import com.atguigu.gulimall.member.entity.MemberReceiveAddressEntity;
import com.atguigu.gulimall.member.mapper.MemberReceiveAddressMapper;
import com.atguigu.gulimall.member.service.MemberReceiveAddressService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author tifa
 * @description 针对表【ums_member_receive_address(会员收货地址)】的数据库操作Service实现
 * @createDate 2025-05-08 21:36:52
 */
@Service
public class MemberReceiveAddressServiceImpl extends
    ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddressEntity>
    implements MemberReceiveAddressService {

  @Override
  public List<MemberReceiveAddressEntity> selectByMemberId(Long memberId) {
    return this.baseMapper.selectList(
        new LambdaQueryWrapper<MemberReceiveAddressEntity>().eq(
            MemberReceiveAddressEntity::getMemberId, memberId));
  }
}




