package com.atguigu.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品库存
 *
 * @TableName wms_ware_sku
 */
@TableName(value = "wms_ware_sku")
public class WareSkuEntity {
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
     * 仓库id
     */
    private Long wareId;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * sku_name
     */
    private String skuName;

    /**
     * 锁定库存
     */
    private Integer stockLocked;

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
     * 库存数
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 库存数
     */
    public void setStock(Integer stock) {
        this.stock = stock;
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
     * 锁定库存
     */
    public Integer getStockLocked() {
        return stockLocked;
    }

    /**
     * 锁定库存
     */
    public void setStockLocked(Integer stockLocked) {
        this.stockLocked = stockLocked;
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
        WareSkuEntity other = (WareSkuEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
                && (this.getWareId() == null ? other.getWareId() == null : this.getWareId().equals(other.getWareId()))
                && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
                && (this.getSkuName() == null ? other.getSkuName() == null : this.getSkuName().equals(other.getSkuName()))
                && (this.getStockLocked() == null ? other.getStockLocked() == null : this.getStockLocked().equals(other.getStockLocked()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getWareId() == null) ? 0 : getWareId().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getSkuName() == null) ? 0 : getSkuName().hashCode());
        result = prime * result + ((getStockLocked() == null) ? 0 : getStockLocked().hashCode());
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
        sb.append(", wareId=").append(wareId);
        sb.append(", stock=").append(stock);
        sb.append(", skuName=").append(skuName);
        sb.append(", stockLocked=").append(stockLocked);
        sb.append("]");
        return sb.toString();
    }
}