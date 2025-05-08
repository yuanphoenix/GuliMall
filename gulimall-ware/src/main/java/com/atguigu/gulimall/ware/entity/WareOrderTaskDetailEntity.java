package com.atguigu.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 库存工作单
 * @TableName wms_ware_order_task_detail
 */
@TableName(value ="wms_ware_order_task_detail")
public class WareOrderTaskDetailEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * sku_name
     */
    private String skuName;

    /**
     * 购买个数
     */
    private Integer skuNum;

    /**
     * 工作单id
     */
    private Long taskId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 1-已锁定  2-已解锁  3-扣减
     */
    private Integer lockStatus;

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
     * sku_id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * sku_id
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * sku_name
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * sku_name
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    /**
     * 购买个数
     */
    public Integer getSkuNum() {
        return skuNum;
    }

    /**
     * 购买个数
     */
    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    /**
     * 工作单id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 工作单id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 仓库id
     */
    public Long getWareId() {
        return wareId;
    }

    /**
     * 仓库id
     */
    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    /**
     * 1-已锁定  2-已解锁  3-扣减
     */
    public Integer getLockStatus() {
        return lockStatus;
    }

    /**
     * 1-已锁定  2-已解锁  3-扣减
     */
    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
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
        WareOrderTaskDetailEntity other = (WareOrderTaskDetailEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuName() == null ? other.getSkuName() == null : this.getSkuName().equals(other.getSkuName()))
            && (this.getSkuNum() == null ? other.getSkuNum() == null : this.getSkuNum().equals(other.getSkuNum()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getWareId() == null ? other.getWareId() == null : this.getWareId().equals(other.getWareId()))
            && (this.getLockStatus() == null ? other.getLockStatus() == null : this.getLockStatus().equals(other.getLockStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuName() == null) ? 0 : getSkuName().hashCode());
        result = prime * result + ((getSkuNum() == null) ? 0 : getSkuNum().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getWareId() == null) ? 0 : getWareId().hashCode());
        result = prime * result + ((getLockStatus() == null) ? 0 : getLockStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuName=").append(skuName);
        sb.append(", skuNum=").append(skuNum);
        sb.append(", taskId=").append(taskId);
        sb.append(", wareId=").append(wareId);
        sb.append(", lockStatus=").append(lockStatus);
        sb.append("]");
        return sb.toString();
    }
}