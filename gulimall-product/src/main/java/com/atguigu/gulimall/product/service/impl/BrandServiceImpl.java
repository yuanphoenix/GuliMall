package com.atguigu.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.gulimall.product.mapper.BrandMapper;
import org.springframework.stereotype.Service;
import utils.PageDTO;
import utils.PageUtils;

import java.util.Map;

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
        return this.page(new PageUtils<BrandEntity>().getPageList(pageDTO), new LambdaQueryWrapper<BrandEntity>().or().like(BrandEntity::getName, key).or().like(BrandEntity::getDescript, key).or().like(BrandEntity::getFirstLetter, key));
    }
}




