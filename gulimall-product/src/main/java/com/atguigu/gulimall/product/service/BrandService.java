package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageDTO;

import java.util.Map;

/**
 * @author tifa
 * @description 针对表【pms_brand(品牌)】的数据库操作Service
 * @createDate 2025-05-08 20:51:50
 */
public interface BrandService extends IService<BrandEntity> {

    IPage<BrandEntity> listPage(PageDTO pageDTO);
}
