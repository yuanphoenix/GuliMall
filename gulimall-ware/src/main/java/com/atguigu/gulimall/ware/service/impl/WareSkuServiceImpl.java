package com.atguigu.gulimall.ware.service.impl;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.atguigu.gulimall.ware.mapper.WareSkuMapper;
import com.atguigu.gulimall.ware.service.WareSkuService;
import com.atguigu.gulimall.ware.vo.WarePageVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import to.SkuHasStockTo;
import to.order.LockTo;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service实现
 * @createDate 2025-05-08 21:20:50
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSkuEntity>
    implements WareSkuService {


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
  @Override
  public List<SkuHasStockTo> getSkuHasStock(List<Long> skuIds) {
    return this.baseMapper.hasStock(skuIds);
  }

  @Transactional
  @Override
  public boolean lock(List<LockTo> lockToList) {
    for (var lockTo : lockToList) {

      List<WareSkuEntity> list = this.list(
          new LambdaQueryWrapper<WareSkuEntity>().eq(WareSkuEntity::getSkuId, lockTo.getSkuId())
              .ge(WareSkuEntity::getStock, lockTo.getStockLocked()));

      boolean hasStock = false;
      for (var c : list) {
        int i = this.baseMapper.lockSku(lockTo, c.getWareId());
        if (i > 0) {
          hasStock = true;
          break;
        }
      }
      if (!hasStock) {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
      }
    }
    return true;
  }
}




