package com.atguigu.gulimall.product;


import com.atguigu.gulimall.product.mapper.BrandMapper;
import com.atguigu.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
        brandMapper.selectList(null).forEach(System.out::println);
        brandService.list().forEach(System.out::println);
    }

}
