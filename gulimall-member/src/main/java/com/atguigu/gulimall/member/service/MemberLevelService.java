package com.atguigu.gulimall.member.service;

import com.atguigu.gulimall.member.entity.MemberLevelEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageDTO;

/**
 * @author tifa
 * @description 针对表【ums_member_level(会员等级)】的数据库操作Service
 * @createDate 2025-05-08 21:36:52
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

  IPage<MemberLevelEntity> listWithPage(PageDTO pageDTO);
}
