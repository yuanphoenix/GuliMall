package com.atguigu.gulimall.ware.service.impl;

import com.atguigu.gulimall.ware.entity.PurchaseDetailEntity;
import com.atguigu.gulimall.ware.entity.PurchaseEntity;
import com.atguigu.gulimall.ware.mapper.PurchaseMapper;
import com.atguigu.gulimall.ware.service.PurchaseDetailService;
import com.atguigu.gulimall.ware.service.PurchaseService;
import com.atguigu.gulimall.ware.service.WareSkuService;
import com.atguigu.gulimall.ware.vo.MergeVo;
import com.atguigu.gulimall.ware.vo.PurchaseDoneVo;
import com.atguigu.gulimall.ware.vo.PurchaseDoneVo.Item;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import constant.WareConstant.PurchaseStatusEnum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tifa
 * @description 针对表【wms_purchase(采购单)】的数据库操作Service实现
 * @createDate 2025-05-08 21:20:50
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, PurchaseEntity>
    implements PurchaseService {

  @Autowired
  private PurchaseDetailService purchaseDetailService;

  @Autowired
  private WareSkuService wareSkuService;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean merge(MergeVo mergeVo) {
    Long purchaseId = mergeVo.getPurchaseId();
    if (mergeVo.getPurchaseId() == null) {
      //新建一个采购单
      PurchaseEntity purchaseEntity = new PurchaseEntity();
      purchaseEntity.setStatus(PurchaseStatusEnum.CREATED.getCode());

      save(purchaseEntity);
      purchaseId = purchaseEntity.getId();
    }

    //拿到了采购单的ID
    PurchaseEntity byId = getById(purchaseId);

    // 检查采购单状态是否允许合并
    //如果采购单的状态不是新建也不是已分配，那么返回false
    if (byId == null || (byId.getStatus() != PurchaseStatusEnum.CREATED.getCode()
        && byId.getStatus() != PurchaseStatusEnum.ASSIGNED.getCode())) {
      return false;
    }

    // 准备更新采购项的列表
    List<Long> items = mergeVo.getItems();
    List<PurchaseDetailEntity> purchaseDetailEntities = purchaseDetailService.listByIds(items);
    items = purchaseDetailEntities.stream()
        .filter(a -> a.getStatus() == PurchaseStatusEnum.ASSIGNED.getCode()
            || a.getStatus() == PurchaseStatusEnum.CREATED.getCode()
        ).map(PurchaseDetailEntity::getId).toList();
    if (items.isEmpty()) {
      return false;
    }

    Long finalPurchaseId = purchaseId;
    var list = items.stream().map(a -> {
      PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
      purchaseDetailEntity.setPurchaseId(finalPurchaseId);
      purchaseDetailEntity.setId(a);
      purchaseDetailEntity.setStatus(PurchaseStatusEnum.ASSIGNED.getCode());
      return purchaseDetailEntity;
    }).toList();

    return purchaseDetailService.updateBatchById(list);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean updateStatus(List<Long> purchaseIds) {
    List<PurchaseEntity> allPurchases = listByIds(purchaseIds);
    //确认采购单是新建或者是已分配状态
    List<PurchaseEntity> toUpdate = allPurchases.stream()
        .filter(p -> p.getStatus() == PurchaseStatusEnum.ASSIGNED.getCode()
            || p.getStatus() == PurchaseStatusEnum.CREATED.getCode())
        .peek(p -> p.setStatus(PurchaseStatusEnum.RECEIVE.getCode())).toList();

    if (toUpdate.isEmpty()) {
      return false;
    }

    //改变采购单的状态
    this.updateBatchById(toUpdate);

    List<Long> updatedIds = toUpdate.stream()
        .map(PurchaseEntity::getId)
        .toList();

    //改变采购项的状态
    purchaseDetailService.update(
        new LambdaUpdateWrapper<PurchaseDetailEntity>()
            .in(PurchaseDetailEntity::getPurchaseId, updatedIds)
            .set(PurchaseDetailEntity::getStatus, PurchaseStatusEnum.BUYING.getCode())
    );
    return true;

  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean finish(PurchaseDoneVo purchaseDoneVo) {
    Long id = purchaseDoneVo.getId();
    PurchaseEntity byId = getById(id);

    if (byId == null || byId.getStatus() == PurchaseStatusEnum.FINISH.getCode()) {
      return false;
    }

    byId.setStatus(PurchaseStatusEnum.FINISH.getCode());
    updateById(byId);

    List<Long> purchaseDetailsIds = purchaseDoneVo.getItems().stream()
        .filter(a -> a.getStatus() == PurchaseStatusEnum.HAVEERROR.getCode())
        .map(Item::getItemId).toList();

    purchaseDetailService.update(new LambdaUpdateWrapper<PurchaseDetailEntity>()
        .in(PurchaseDetailEntity::getId, purchaseDetailsIds)
        .set(PurchaseDetailEntity::getStatus, PurchaseStatusEnum.HAVEERROR.getCode())
    );

    purchaseDetailsIds = purchaseDoneVo.getItems().stream()
        .filter(a -> a.getStatus() == PurchaseStatusEnum.FINISH.getCode())
        .map(Item::getItemId).toList();

    purchaseDetailService.update(new LambdaUpdateWrapper<PurchaseDetailEntity>()
        .in(PurchaseDetailEntity::getId, purchaseDetailsIds)
        .set(PurchaseDetailEntity::getStatus, PurchaseStatusEnum.FINISH.getCode())
    );

    //更新完状态后，应该增加库存
    List<PurchaseDetailEntity> list = purchaseDetailService.list(
        new LambdaQueryWrapper<PurchaseDetailEntity>().in(PurchaseDetailEntity::getId,
            purchaseDetailsIds));
    for (PurchaseDetailEntity purchaseDetailEntity : list) {
      wareSkuService.addStock(purchaseDetailEntity.getWareId(), purchaseDetailEntity.getSkuId(),
          purchaseDetailEntity.getSkuNum());
    }

    return true;
  }

}




