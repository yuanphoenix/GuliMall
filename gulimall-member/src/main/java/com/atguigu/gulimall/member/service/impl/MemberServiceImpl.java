package com.atguigu.gulimall.member.service.impl;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.atguigu.gulimall.member.entity.MemberLevelEntity;
import com.atguigu.gulimall.member.mapper.MemberMapper;
import com.atguigu.gulimall.member.service.MemberLevelService;
import com.atguigu.gulimall.member.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exception.BizCodeEnum;
import exception.BizException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tifa
 * @description 针对表【ums_member(会员)】的数据库操作Service实现
 * @createDate 2025-05-08 21:36:52
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity>
    implements MemberService {

  @Autowired
  private MemberLevelService memberLevelService;


  /**
   *
   * @param member
   * @return
   */
  @Override
  public boolean saveMember(MemberEntity member) {
    if (member.getMobile() == null) {
      throw new BizException(BizCodeEnum.STYLE_EXCEPTION, "没找到手机号");
    }
    var memberEntity = this.baseMapper.selectOne(
        new LambdaQueryWrapper<MemberEntity>().eq(MemberEntity::getMobile, member.getMobile()));
    if (memberEntity != null) {
      throw new BizException(BizCodeEnum.STYLE_EXCEPTION, "已经存在相同的手机号");
    }
    member.setCreateTime(LocalDateTime.now());
    MemberLevelEntity one = memberLevelService.getOne(
        new LambdaQueryWrapper<MemberLevelEntity>().eq(MemberLevelEntity::getDefaultStatus, 1));
    if (one != null) {
      member.setLevelId(one.getId());
    }
    return this.baseMapper.insert(member) == 1;
  }
}




