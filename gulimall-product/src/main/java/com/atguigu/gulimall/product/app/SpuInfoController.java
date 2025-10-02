package com.atguigu.gulimall.product.app;

import com.atguigu.gulimall.product.entity.SpuInfoEntity;
import com.atguigu.gulimall.product.service.SpuInfoService;
import com.atguigu.gulimall.product.vo.SpuPageVo;
import com.atguigu.gulimall.product.vo.spuinfo.SpuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.R;

/**
 * <p>
 * spu信息 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/spuinfo")
public class SpuInfoController {

  @Autowired
  private SpuInfoService spuInfoService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list(@ModelAttribute SpuPageVo pageDTO) {
    IPage<SpuInfoEntity> list = spuInfoService.pageWithCondition(pageDTO);
    return R.ok().put("page", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    SpuInfoEntity entity = spuInfoService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody SpuInfoVo spuInfoVo) {
    boolean saved = spuInfoService.saveSpu(spuInfoVo);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody SpuInfoEntity spuInfo) {
    boolean updated = spuInfoService.updateById(spuInfo);
    return updated ? R.ok() : R.error();
  }

  /**
   *
   * 商品上架接口
   *
   * @param id
   * @return
   */
  @PostMapping("/{spuid}/up")
  public R up(@PathVariable("spuid") Long id) {
    spuInfoService.up(id);
    return R.ok();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = spuInfoService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
