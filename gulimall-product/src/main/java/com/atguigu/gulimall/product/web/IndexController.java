package com.atguigu.gulimall.product.web;


import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {


  @Autowired
  private CategoryService categoryService;

  @GetMapping({"/", "/index.html"})
  public String indexPage(Model model) {

    List<CategoryEntity> categoryEntities = categoryService.selectLevelOneCategorys();
    model.addAttribute("categorys", categoryEntities);
    return "index";
  }


  @ResponseBody
  @GetMapping("/index/catalog.json")
  public Map<String, List<Catelog2Vo>> getCatalogJson() {
    return categoryService.getCatalogJson();
  }

}
