package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.mapper.BrandMapper;
import com.atguigu.gulimall.product.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import utils.PageDTO;
import utils.PageUtils;

/**
 * @author tifa
 * @description 针对表【pms_brand(品牌)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandEntity>
        implements BrandService {


    @Override
    public IPage<BrandEntity> listPage(PageDTO pageDTO) {
        String key = pageDTO.getKey();
        return this.page(PageUtils.of(pageDTO), new LambdaQueryWrapper<BrandEntity>().or().like(BrandEntity::getName, key).or().like(BrandEntity::getDescript, key).or().like(BrandEntity::getFirstLetter, key));
    }
}




