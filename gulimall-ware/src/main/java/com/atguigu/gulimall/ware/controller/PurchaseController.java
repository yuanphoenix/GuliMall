package com.atguigu.gulimall.ware.controller;

import com.atguigu.gulimall.ware.entity.PurchaseEntity;
import com.atguigu.gulimall.ware.service.PurchaseService;
import com.atguigu.gulimall.ware.vo.MergeVo;
import com.atguigu.gulimall.ware.vo.PurchaseDoneVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.PageDTO;
import utils.PageUtils;
import utils.R;

/**
 * <p>
 * 采购单 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/ware/purchase")
public class PurchaseController {

  @Autowired
  private PurchaseService purchaseService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list(@ModelAttribute PageDTO pageDTO) {
    IPage<PurchaseEntity> list = purchaseService.page(PageUtils.of(pageDTO));
    return R.ok().put("page", list);
  }

  /// ware/purchase/merge
  @PostMapping("/merge")
  public R merge(@RequestBody MergeVo mergeVo) {
    return purchaseService.merge(mergeVo) ? R.ok().put("msg", "success") : R.error();
  }

  //ware/purchase/received

  /**
   * 采购人员领取采购单
   * @param purchaseIds
   * @return
   */
  @PostMapping("/received")
  public R received(@RequestBody List<Long> purchaseIds) {
    boolean success = purchaseService.updateStatus(purchaseIds);
    return success ? R.ok().put("msg", "success") : R.error();
  }

  //ware/purchase/done
  @PostMapping("/done")
  public R finish(@RequestBody PurchaseDoneVo purchaseDoneVo) {
    boolean success = purchaseService.finish(purchaseDoneVo);
    return R.ok();
  }

  @GetMapping("/unreceive/list")
  public R listUnreceive() {
    IPage<PurchaseEntity> list = purchaseService.page(Page.of(1, 10),
        new LambdaQueryWrapper<PurchaseEntity>().eq(PurchaseEntity::getStatus, 0).or()
            .eq(PurchaseEntity::getStatus, 1));
    return R.ok().put("page", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    PurchaseEntity entity = purchaseService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody PurchaseEntity purchase) {
    boolean saved = purchaseService.save(purchase);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody PurchaseEntity purchase) {
    boolean updated = purchaseService.updateById(purchase);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = purchaseService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
