package com.atguigu.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 订单操作历史记录
 * @TableName oms_order_operate_history
 */
@TableName(value ="oms_order_operate_history")
public class OrderOperateHistoryEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 操作人[用户；系统；后台管理员]
     */
    private String operateMan;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;

    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
     */
    private Integer orderStatus;

    /**
     * 备注
     */
    private String note;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 操作人[用户；系统；后台管理员]
     */
    public String getOperateMan() {
        return operateMan;
    }

    /**
     * 操作人[用户；系统；后台管理员]
     */
    public void setOperateMan(String operateMan) {
        this.operateMan = operateMan;
    }

    /**
     * 操作时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 操作时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 备注
     */
    public void setNote(String note) {
        this.note = note;
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
        OrderOperateHistoryEntity other = (OrderOperateHistoryEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOperateMan() == null ? other.getOperateMan() == null : this.getOperateMan().equals(other.getOperateMan()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOperateMan() == null) ? 0 : getOperateMan().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", operateMan=").append(operateMan);
        sb.append(", createTime=").append(createTime);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", note=").append(note);
        sb.append("]");
        return sb.toString();
    }
}