package com.atguigu.gulimall.product.vo;

import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.util.ArrayList;
import java.util.List;

public class AttrGroupResponseVo {

  @JsonUnwrapped
  private AttrGroupEntity attrEntity;
  private List<AttrVo> attrs = new ArrayList<>();

  public AttrGroupEntity getAttrEntity() {
    return attrEntity;
  }

  public void setAttrEntity(AttrGroupEntity attrEntity) {
    this.attrEntity = attrEntity;
  }

  public List<AttrVo> getAttrs() {
    return attrs;
  }

  public void setAttrs(List<AttrVo> attrs) {
    this.attrs = attrs;
  }
}
