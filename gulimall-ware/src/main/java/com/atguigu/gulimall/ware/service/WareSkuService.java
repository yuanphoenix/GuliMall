package com.atguigu.gulimall.ware.service;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.atguigu.gulimall.ware.vo.WarePageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import constant.RabbitMqMessageEnum;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import to.SkuHasStockTo;
import to.ware.WareTo;

/**
 * @author tifa
 * @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service
 * @createDate 2025-05-08 21:20:50
 */
public interface WareSkuService extends IService<WareSkuEntity> {

  IPage<WareSkuEntity> pageWithCondition(WarePageVo pageDTO);


  /**
   * 增加库存
   *
   * @param wareId
   * @param skuId
   * @param skuNum
   */
  void addStock(Long wareId, Long skuId, Integer skuNum);

  List<SkuHasStockTo> getSkuHasStock(List<Long> skuIds);

  // 锁库存
  boolean lockStock(WareTo lockTo);


  /**
   * 解除库存。
   * <p>
   * 下订单，过期没有支付，系统自动取消。 用户手动取消库存 为了保证一致性而取消
   *
   * @return
   */
  @NotNull RabbitMqMessageEnum unlockStock(WareTo wareTo);


  /**
   * 扣除库存
   *
   * @param orderSn
   * @return
   */
  RabbitMqMessageEnum minusStock(String orderSn);
}
