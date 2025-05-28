package com.atguigu.gulimall.product.vo;

public class TreeVoRequest {
    private Long draggingNodeId;  // 被拖拽的节点ID
    private Long dropNodeId;     // 目标节点ID
    private String dropType;     // "prev", "next", "inner"

    public Long getDraggingNodeId() {
        return draggingNodeId;
    }

    public void setDraggingNodeId(Long draggingNodeId) {
        this.draggingNodeId = draggingNodeId;
    }

    public Long getDropNodeId() {
        return dropNodeId;
    }

    public void setDropNodeId(Long dropNodeId) {
        this.dropNodeId = dropNodeId;
    }

    public String getDropType() {
        return dropType;
    }

    public void setDropType(String dropType) {
        this.dropType = dropType;
    }
}