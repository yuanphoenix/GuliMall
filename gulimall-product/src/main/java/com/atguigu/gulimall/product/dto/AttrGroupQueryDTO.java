package com.atguigu.gulimall.product.dto;

import utils.PageDTO;

public class AttrGroupQueryDTO extends PageDTO {
    private Long t;         // 时间戳
    private String key;     // 搜索关键词

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
