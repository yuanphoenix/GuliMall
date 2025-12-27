package com.atguigu.gulimall.search.service;

import java.util.List;
import to.es.SkuEsModel;

public interface ProductSaveService {


  void productStatusUp(List<SkuEsModel> skuEsModels);
}
