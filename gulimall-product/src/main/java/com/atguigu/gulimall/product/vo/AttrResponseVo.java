package com.atguigu.gulimall.product.vo;

import java.util.List;

public class AttrResponseVo extends AttrVo {
    private String catalogName;
    private List<Long> catalogPath;
    private String groupName;
    private Long attrGroupId;


    @Override
    public Long getAttrGroupId() {
        return attrGroupId;
    }

    @Override
    public void setAttrGroupId(Long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public List<Long> getCatalogPath() {
        return catalogPath;
    }

    public void setCatalogPath(List<Long> catalogPath) {
        this.catalogPath = catalogPath;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
