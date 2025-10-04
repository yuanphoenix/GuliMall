package com.atguigu.gulimall.search.service;

import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;

public interface MallSearchService {

  /**
   * 根据前端传递过来的参数进行检索，返回各个商品。
   * @param searchParam
   * @return
   */
  SearchResult search(SearchParam searchParam);
}
