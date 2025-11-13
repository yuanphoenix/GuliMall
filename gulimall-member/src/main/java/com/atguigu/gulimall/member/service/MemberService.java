package com.atguigu.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.member.entity.MemberEntity;
import to.MemberEntityVo;

/**
 * @author tifa
 * @description 针对表【ums_member(会员)】的数据库操作Service
 * @createDate 2025-05-08 21:36:52
 */
public interface MemberService extends IService<MemberEntity> {


  boolean saveMember(MemberEntity member);

  MemberEntityVo checkLogin(MemberEntity member);
}
