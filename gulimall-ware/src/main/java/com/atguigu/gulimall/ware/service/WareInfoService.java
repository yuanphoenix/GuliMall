package com.atguigu.gulimall.ware.service;

import com.atguigu.gulimall.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageDTO;

/**
 * @author tifa
 * @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service
 * @createDate 2025-05-08 21:20:50
 */
public interface WareInfoService extends IService<WareInfoEntity> {

  IPage<WareInfoEntity> pageWithCondition(PageDTO pageDTO);
}
