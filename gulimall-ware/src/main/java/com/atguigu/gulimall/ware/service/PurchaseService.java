package com.atguigu.gulimall.ware.service;

import com.atguigu.gulimall.ware.entity.PurchaseEntity;
import com.atguigu.gulimall.ware.vo.MergeVo;
import com.atguigu.gulimall.ware.vo.PurchaseDoneVo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【wms_purchase(采购单)】的数据库操作Service
 * @createDate 2025-05-08 21:20:50
 */
public interface PurchaseService extends IService<PurchaseEntity> {

  boolean merge(MergeVo mergeVo);

  boolean updateStatus(List<Long> purchaseIds);

  boolean finish(PurchaseDoneVo purchaseDoneVo);
}
