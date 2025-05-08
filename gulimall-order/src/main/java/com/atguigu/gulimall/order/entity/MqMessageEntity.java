package com.atguigu.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 
 * @TableName mq_message
 */
@TableName(value ="mq_message")
public class MqMessageEntity {
    /**
     * 
     */
    @TableId
    private String messageId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String toExchane;

    /**
     * 
     */
    private String routingKey;

    /**
     * 
     */
    private String classType;

    /**
     * 0-新建 1-已发送 2-错误抵达 3-已抵达
     */
    private Integer messageStatus;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    /**
     * 
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public String getToExchane() {
        return toExchane;
    }

    /**
     * 
     */
    public void setToExchane(String toExchane) {
        this.toExchane = toExchane;
    }

    /**
     * 
     */
    public String getRoutingKey() {
        return routingKey;
    }

    /**
     * 
     */
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    /**
     * 
     */
    public String getClassType() {
        return classType;
    }

    /**
     * 
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * 0-新建 1-已发送 2-错误抵达 3-已抵达
     */
    public Integer getMessageStatus() {
        return messageStatus;
    }

    /**
     * 0-新建 1-已发送 2-错误抵达 3-已抵达
     */
    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * 
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MqMessageEntity other = (MqMessageEntity) that;
        return (this.getMessageId() == null ? other.getMessageId() == null : this.getMessageId().equals(other.getMessageId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getToExchane() == null ? other.getToExchane() == null : this.getToExchane().equals(other.getToExchane()))
            && (this.getRoutingKey() == null ? other.getRoutingKey() == null : this.getRoutingKey().equals(other.getRoutingKey()))
            && (this.getClassType() == null ? other.getClassType() == null : this.getClassType().equals(other.getClassType()))
            && (this.getMessageStatus() == null ? other.getMessageStatus() == null : this.getMessageStatus().equals(other.getMessageStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMessageId() == null) ? 0 : getMessageId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getToExchane() == null) ? 0 : getToExchane().hashCode());
        result = prime * result + ((getRoutingKey() == null) ? 0 : getRoutingKey().hashCode());
        result = prime * result + ((getClassType() == null) ? 0 : getClassType().hashCode());
        result = prime * result + ((getMessageStatus() == null) ? 0 : getMessageStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageId=").append(messageId);
        sb.append(", content=").append(content);
        sb.append(", toExchane=").append(toExchane);
        sb.append(", routingKey=").append(routingKey);
        sb.append(", classType=").append(classType);
        sb.append(", messageStatus=").append(messageStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}