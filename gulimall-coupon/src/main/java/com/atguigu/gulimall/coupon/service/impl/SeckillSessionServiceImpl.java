package com.atguigu.gulimall.coupon.service.impl;

import com.atguigu.gulimall.coupon.entity.SeckillSessionEntity;
import com.atguigu.gulimall.coupon.entity.SeckillSkuRelationEntity;
import com.atguigu.gulimall.coupon.mapper.SeckillSessionMapper;
import com.atguigu.gulimall.coupon.service.SeckillSessionService;
import com.atguigu.gulimall.coupon.service.SeckillSkuRelationService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import utils.PageDTO;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【sms_seckill_session(秒杀活动场次)】的数据库操作Service实现
 * @createDate 2025-05-08 21:07:54
 */
@Service
public class SeckillSessionServiceImpl extends
    ServiceImpl<SeckillSessionMapper, SeckillSessionEntity>
    implements SeckillSessionService {

  @Autowired
  private SeckillSkuRelationService seckillSkuRelationService;


  @Override
  public IPage<SeckillSessionEntity> list(PageDTO pageDTO) {
    return this.page(PageUtils.of(pageDTO));
  }

  @Override
  public @NonNull List<SeckillSessionEntity> getSecKill3daysLatest() {
    LocalDateTime begin = LocalDate.now().minusDays(3).atStartOfDay();
    var sessions = this.lambdaQuery().gt(SeckillSessionEntity::getStartTime, begin).list();
    if (sessions.isEmpty()) {
      return List.of();
    }
    List<Long> sessionId = sessions.stream().map(SeckillSessionEntity::getId).toList();

    var relationMap = seckillSkuRelationService.lambdaQuery().in(
            SeckillSkuRelationEntity::getPromotionSessionId, sessionId).list().stream()
        .collect(Collectors.groupingBy(SeckillSkuRelationEntity::getPromotionSessionId));

    sessions.forEach(session -> session.setSeckillSkuRelationEntities(
        relationMap.getOrDefault(session.getId(), List.of())));
    return sessions;
  }
}




