package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.SpuInfoEntity;
import com.atguigu.gulimall.product.vo.SpuPageVo;
import com.atguigu.gulimall.product.vo.spuinfo.SpuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tifa
 * @description 针对表【pms_spu_info(spu信息)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

  /**
   * 这个是很复杂的前端上传商品的各种信息到后端。前端还涉及到了笛卡尔积的那个。
   *
   * @param spuInfoVo
   * @return
   */
  boolean saveSpu(SpuInfoVo spuInfoVo);

  IPage<SpuInfoEntity> pageWithCondition(SpuPageVo pageDTO);

  void up(Long id);

  List<SpuInfoEntity> listSpuByIds(List<Long> spuIds);
}
