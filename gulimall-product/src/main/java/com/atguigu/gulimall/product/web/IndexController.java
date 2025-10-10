package com.atguigu.gulimall.product.web;


import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.service.SkuInfoService;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import com.atguigu.gulimall.product.vo.SkuItemVo;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final CategoryService categoryService;
  private final SkuInfoService skuInfoService;

  public IndexController(CategoryService categoryService, SkuInfoService skuInfoService) {
    this.categoryService = categoryService;
    this.skuInfoService = skuInfoService;
  }

  @GetMapping({"/", "/index.html"})
  public String indexPage(Model model) {
    List<CategoryEntity> categoryEntities = categoryService.selectLevelOneCategorys();
    model.addAttribute("categorys", categoryEntities);
    return "index";
  }

  /**
   * 展示当前sku的详情
   *
   * @param skuId
   * @return
   */
  @GetMapping("/{skuId}.html")
  public String skuItem(@PathVariable("skuId") Long skuId) {
    logger.info("准备查询{}的详情", skuId);
    SkuItemVo skuItemVo = skuInfoService.item(skuId);
    return "item";
  }


  @ResponseBody
  @GetMapping("/index/catalog.json")
  public Map<String, List<Catelog2Vo>> getCatalogJson() {
    return categoryService.getCatalogJson();
  }

}
