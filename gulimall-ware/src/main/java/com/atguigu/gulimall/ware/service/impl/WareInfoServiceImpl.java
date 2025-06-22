package com.atguigu.gulimall.ware.service.impl;

import com.atguigu.gulimall.ware.entity.WareInfoEntity;
import com.atguigu.gulimall.ware.mapper.WareInfoMapper;
import com.atguigu.gulimall.ware.service.WareInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import utils.PageDTO;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service实现
 * @createDate 2025-05-08 21:20:50
 */
@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfoEntity>
    implements WareInfoService {

  @Override
  public IPage<WareInfoEntity> pageWithCondition(PageDTO pageDTO) {
    return page(PageUtils.of(pageDTO),
        new LambdaQueryWrapper<WareInfoEntity>()
            .and(StringUtils.isNoneEmpty(pageDTO.getKey()), w -> {
              w.eq(NumberUtils.isCreatable(pageDTO.getKey()), WareInfoEntity::getId,NumberUtils.toLong(pageDTO.getKey()))
                  .or()
                  .like(WareInfoEntity::getName, pageDTO.getKey());
            })
    );
  }
}




