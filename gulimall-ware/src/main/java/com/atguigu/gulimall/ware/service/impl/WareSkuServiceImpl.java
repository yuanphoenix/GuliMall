package com.atguigu.gulimall.ware.service.impl;

import com.atguigu.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.atguigu.gulimall.ware.entity.WareOrderTaskEntity;
import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.atguigu.gulimall.ware.mapper.WareOrderTaskDetailMapper;
import com.atguigu.gulimall.ware.mapper.WareOrderTaskMapper;
import com.atguigu.gulimall.ware.mapper.WareSkuMapper;
import com.atguigu.gulimall.ware.service.WareSkuService;
import com.atguigu.gulimall.ware.vo.WarePageVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import to.SkuHasStockTo;
import to.ware.WareItemTo;
import to.ware.WareTo;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service实现
 * @createDate 2025-05-08 21:20:50
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSkuEntity>
    implements WareSkuService {

  private final RabbitTemplate rabbitTemplate;


  private final WareOrderTaskMapper wareOrderTaskMapper;


  private final WareOrderTaskDetailMapper wareOrderTaskDetailMapper;

  public WareSkuServiceImpl(RabbitTemplate rabbitTemplate, WareOrderTaskMapper wareOrderTaskMapper,
      WareOrderTaskDetailMapper wareOrderTaskDetailMapper) {
    this.rabbitTemplate = rabbitTemplate;
    this.wareOrderTaskMapper = wareOrderTaskMapper;
    this.wareOrderTaskDetailMapper = wareOrderTaskDetailMapper;
  }


  @Override
  public IPage<WareSkuEntity> pageWithCondition(WarePageVo pageDTO) {
    return page(PageUtils.of(pageDTO), new LambdaQueryWrapper<WareSkuEntity>()
        .eq(!ObjectUtils.isEmpty(pageDTO.getSkuId()), WareSkuEntity::getSkuId, pageDTO.getSkuId())
        .eq(!ObjectUtils.isEmpty(pageDTO.getWareId()), WareSkuEntity::getWareId,
            pageDTO.getWareId()));
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void addStock(Long wareId, Long skuId, Integer skuNum) {
    var wareSkuEntities = baseMapper.selectOne(
        new LambdaQueryWrapper<WareSkuEntity>().eq(WareSkuEntity::getWareId, wareId)
            .eq(WareSkuEntity::getSkuId, skuId));
    if (wareSkuEntities != null) {
      baseMapper.addStock(wareId, skuId, skuNum);
    } else {
      WareSkuEntity wareSkuEntity = new WareSkuEntity();
      wareSkuEntity.setWareId(wareId);
      wareSkuEntity.setSkuId(skuId);
      wareSkuEntity.setStock(skuNum);
      save(wareSkuEntity);
    }

  }

  /**
   * 根据skuid查询是否有库存
   *
   * @param skuIds 被查询的skuid列表
   * @return
   */
  @Transactional(readOnly = true)
  @Override
  public List<SkuHasStockTo> getSkuHasStock(List<Long> skuIds) {
    if (skuIds == null || skuIds.isEmpty()) {
      return List.of();
    }
    skuIds = skuIds.stream().distinct().toList();
    return this.baseMapper.hasStock(skuIds);
  }

  @Transactional
  @Override
  public boolean lockStock(WareTo wareTo) {

    List<WareItemTo> wareItemToList = wareTo.getWareItemToList();

    for (var lockTo : wareItemToList) {
//      检查出来有库存的仓库，可能有多个
      boolean success = false;
      List<WareSkuEntity> wareList = this.list(
          new LambdaQueryWrapper<WareSkuEntity>().eq(WareSkuEntity::getSkuId, lockTo.getSkuId())
              .apply("stock-stock_locked>={0}", lockTo.getSkuNum()));
//尝试占用
      for (var c : wareList) {
        int i = this.baseMapper.lockSku(lockTo, c.getWareId());
        lockTo.setWareId(c.getWareId());
        if (i != 1) {
          continue;
        }
//      占用成功
        success = true;
        break;
      }
      if (!success) {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
      }
    }

    WareOrderTaskEntity wareOrderTask = new WareOrderTaskEntity();
    BeanUtils.copyProperties(wareTo, wareOrderTask);
    wareOrderTaskMapper.insert(wareOrderTask);
    Long id = wareOrderTask.getId();
    List<WareOrderTaskDetailEntity> list = wareItemToList.stream().map(a -> {
      WareOrderTaskDetailEntity wareOrderTaskDetailEntity = new WareOrderTaskDetailEntity();
      BeanUtils.copyProperties(a, wareOrderTaskDetailEntity);
      wareOrderTaskDetailEntity.setTaskId(id);
      return wareOrderTaskDetailEntity;
    }).toList();
    wareOrderTaskDetailMapper.insert(list);
    rabbitTemplate.convertAndSend("stock-event-exchange", "stock.delay.stock", wareTo);
    return true;
  }

  @Override
  @Transactional
  public void unlockStock(WareTo wareTo) {
    //OrderSn是必然有的
    WareOrderTaskEntity wareOrderTaskEntity = wareOrderTaskMapper.selectOne(
        new LambdaQueryWrapper<WareOrderTaskEntity>().eq(WareOrderTaskEntity::getOrderSn,
            wareTo.getOrderSn()));
    if (wareOrderTaskEntity == null) {
      log.warn("释放库存发现并没有记录");
      return;
    }
    List<WareOrderTaskDetailEntity> wareOrderTaskDetailEntityList = wareOrderTaskDetailMapper.selectList(
        new LambdaQueryWrapper<WareOrderTaskDetailEntity>()
            .eq(WareOrderTaskDetailEntity::getLockStatus, 1)
            .eq(WareOrderTaskDetailEntity::getTaskId, wareOrderTaskEntity.getId()));

    wareOrderTaskDetailEntityList.forEach(a -> {

      int update = wareOrderTaskDetailMapper.update(null,
          new LambdaUpdateWrapper<WareOrderTaskDetailEntity>()
              .eq(WareOrderTaskDetailEntity::getId, a.getId())
              .eq(WareOrderTaskDetailEntity::getLockStatus, 1)
              .set(WareOrderTaskDetailEntity::getLockStatus, 2)
      );
      if (update == 1) {
        Long skuId = a.getSkuId();
        Integer skuNum = a.getSkuNum();
        Long wareId = a.getWareId();
        this.baseMapper.unlock(skuId, wareId, skuNum);
      }
    });
  }
}




