package com.tifa.gulimallseckill.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import to.seckill.SeckillSkuRelationEntityTo;


@Data
public class SeckillSessionEntityTo {

  /**
   * id
   */
  private Long id;

  /**
   * 场次名称
   */
  private String name;

  /**
   * 每日开始时间
   */
  private LocalDateTime startTime;

  /**
   * 每日结束时间
   */
  private LocalDateTime endTime;

  /**
   * 启用状态
   */
  private Integer status;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  private List<SeckillSkuRelationEntityTo> seckillSkuRelationEntities;
}
