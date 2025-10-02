package com.atguigu.gulimall.search.controller;


import com.atguigu.gulimall.search.service.ProductSaveService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.es.SkuEsModel;
import utils.R;

@RequestMapping("/search")
@RestController
public class EsSaveController {


  private static final Logger log = LoggerFactory.getLogger(EsSaveController.class);
  private final ProductSaveService productSaveService;

  public EsSaveController(ProductSaveService productSaveService) {
    this.productSaveService = productSaveService;
  }

  @PostMapping("/save/product")
  public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
    try {
      productSaveService.productStatusUp(skuEsModels);
    } catch (Exception e) {
      return R.error();
    }
    return R.ok();
  }
}
