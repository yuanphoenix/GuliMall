package com.atguigu.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 采购单
 * @TableName wms_purchase
 */
@TableName(value ="wms_purchase")
public class PurchaseEntity {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long assigneeId;

    /**
     * 
     */
    private String assigneeName;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private Integer priority;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Long wareId;

    /**
     * 
     */
    private BigDecimal amount;

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
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public Long getAssigneeId() {
        return assigneeId;
    }

    /**
     * 
     */
    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    /**
     * 
     */
    public String getAssigneeName() {
        return assigneeName;
    }

    /**
     * 
     */
    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    /**
     * 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     */
    public Long getWareId() {
        return wareId;
    }

    /**
     * 
     */
    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    /**
     * 
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        PurchaseEntity other = (PurchaseEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAssigneeId() == null ? other.getAssigneeId() == null : this.getAssigneeId().equals(other.getAssigneeId()))
            && (this.getAssigneeName() == null ? other.getAssigneeName() == null : this.getAssigneeName().equals(other.getAssigneeName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getWareId() == null ? other.getWareId() == null : this.getWareId().equals(other.getWareId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAssigneeId() == null) ? 0 : getAssigneeId().hashCode());
        result = prime * result + ((getAssigneeName() == null) ? 0 : getAssigneeName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getWareId() == null) ? 0 : getWareId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
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
        sb.append(", id=").append(id);
        sb.append(", assigneeId=").append(assigneeId);
        sb.append(", assigneeName=").append(assigneeName);
        sb.append(", phone=").append(phone);
        sb.append(", priority=").append(priority);
        sb.append(", status=").append(status);
        sb.append(", wareId=").append(wareId);
        sb.append(", amount=").append(amount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}