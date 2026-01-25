package com.atguigu.gulimall.ware.controller;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.atguigu.gulimall.ware.service.WareSkuService;
import com.atguigu.gulimall.ware.vo.WarePageVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.SkuHasStockTo;
import to.order.LockSkuTo;
import utils.R;

/**
 * <p>
 * 商品库存 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/ware/waresku")
public class WareSkuController {

  @Autowired
  private WareSkuService wareSkuService;


  @PostMapping("/hasstock")
  public R getSkuHasStock(@RequestBody List<Long> skuIds) {
    List<SkuHasStockTo> list = wareSkuService.getSkuHasStock(skuIds);
    return R.ok().put("data", list);
  }

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list(@ModelAttribute WarePageVo pageDTO) {
    IPage<WareSkuEntity> list = wareSkuService.pageWithCondition(pageDTO);
    return R.ok().put("page", list);
  }


  @PostMapping("/lockStcok")
  public R lockStock(@RequestBody List<LockSkuTo> lockToList) {
    boolean result = wareSkuService.lockStock(lockToList);
    return result ? R.ok() : R.error();
  }




  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    WareSkuEntity entity = wareSkuService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody WareSkuEntity wareSku) {
    boolean saved = wareSkuService.save(wareSku);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody WareSkuEntity wareSku) {
    boolean updated = wareSkuService.updateById(wareSku);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = wareSkuService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
