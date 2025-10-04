package com.atguigu.gulimall.search.web;

import com.atguigu.gulimall.search.service.MallSearchService;
import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @Autowired
  private MallSearchService mallSearchService;

  @GetMapping("/list.html")
  public String listPage(SearchParam searchParam, Model model) {

    SearchResult result = mallSearchService.search(searchParam);
    model.addAttribute("result", result);
    return "list";
  }

}
