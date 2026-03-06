package com.atguigu.gulimall.member.service;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import to.MemberEntityVo;
import to.order.OrderInfoTo;
import utils.R;

/**
 * @author tifa
 * @description 针对表【ums_member(会员)】的数据库操作Service
 * @createDate 2025-05-08 21:36:52
 */
public interface MemberService extends IService<MemberEntity> {


  /**
   * 我不明白为什么这个账单有关的接口要放在member微服务里，但是视频里就是这么写的。
   *
   * @param memberEntityVo
   * @param orderInfoTo
   * @return
   */
  R getOrderList(MemberEntityVo memberEntityVo, OrderInfoTo orderInfoTo);

  boolean saveMember(MemberEntity member);

  MemberEntityVo checkLogin(MemberEntity member);


  void sendPayed(String orderSn);
}
