package com.atguigu.gulimall.product.vo;

import java.util.List;

public class AttrResponseVo extends AttrVo {
    private String catalogName;
    private List<Integer> catalogPath;
    private String groupName;


    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public List<Integer> getCatalogPath() {
        return catalogPath;
    }

    public void setCatalogPath(List<Integer> catalogPath) {
        this.catalogPath = catalogPath;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
