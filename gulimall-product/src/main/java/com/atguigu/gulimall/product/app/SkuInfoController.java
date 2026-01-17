package com.atguigu.gulimall.product.app;

import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.service.SkuInfoService;
import com.atguigu.gulimall.product.vo.SpuPageVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import to.cart.CartItem;
import utils.R;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/skuinfo")
public class SkuInfoController {

  @Autowired
  private SkuInfoService skuInfoService;


  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list(@ModelAttribute SpuPageVo pageDTO) {
    IPage<SkuInfoEntity> list = skuInfoService.pageWithCondition(pageDTO);
    return R.ok().put("page", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    SkuInfoEntity entity = skuInfoService.getById(id);
    return R.ok().put("data", entity);
  }


  @PostMapping("/paylist")
  public List<to.cart.CartItem> skuInfoEntityList(
      @RequestBody List<to.cart.CartItem> cartItemList) {
    List<SkuInfoEntity> list = skuInfoService.list(
        new LambdaQueryWrapper<SkuInfoEntity>().in(SkuInfoEntity::getSkuId,
            cartItemList.stream().map(
                CartItem::getSkuId).toList()));
    return list.stream().map(a -> {
      CartItem cartItem = new CartItem();
      cartItem.setSkuId(a.getSkuId());
      cartItem.setPrice(a.getPrice());
      return cartItem;
    }).toList();


  }


  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody SkuInfoEntity skuInfo) {
    boolean saved = skuInfoService.save(skuInfo);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody SkuInfoEntity skuInfo) {
    boolean updated = skuInfoService.updateById(skuInfo);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = skuInfoService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
