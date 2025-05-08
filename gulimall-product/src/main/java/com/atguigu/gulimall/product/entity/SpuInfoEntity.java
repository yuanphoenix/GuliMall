package com.atguigu.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * spu信息
 * @TableName pms_spu_info
 */
@TableName(value ="pms_spu_info")
public class SpuInfoEntity {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品描述
     */
    private String spuDescription;

    /**
     * 所属分类id
     */
    private Long catalogId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 
     */
    private BigDecimal weight;

    /**
     * 上架状态[0 - 新建，1 - 上架，2 - 下架]
     */
    private Integer publishStatus;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    /**
     * 商品id
     */
    public Long getId() {
        return id;
    }

    /**
     * 商品id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品名称
     */
    public String getSpuName() {
        return spuName;
    }

    /**
     * 商品名称
     */
    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    /**
     * 商品描述
     */
    public String getSpuDescription() {
        return spuDescription;
    }

    /**
     * 商品描述
     */
    public void setSpuDescription(String spuDescription) {
        this.spuDescription = spuDescription;
    }

    /**
     * 所属分类id
     */
    public Long getCatalogId() {
        return catalogId;
    }

    /**
     * 所属分类id
     */
    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    /**
     * 品牌id
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 品牌id
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * 上架状态[0 - 新建，1 - 上架，2 - 下架]
     */
    public Integer getPublishStatus() {
        return publishStatus;
    }

    /**
     * 上架状态[0 - 新建，1 - 上架，2 - 下架]
     */
    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
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
        SpuInfoEntity other = (SpuInfoEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuName() == null ? other.getSpuName() == null : this.getSpuName().equals(other.getSpuName()))
            && (this.getSpuDescription() == null ? other.getSpuDescription() == null : this.getSpuDescription().equals(other.getSpuDescription()))
            && (this.getCatalogId() == null ? other.getCatalogId() == null : this.getCatalogId().equals(other.getCatalogId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getPublishStatus() == null ? other.getPublishStatus() == null : this.getPublishStatus().equals(other.getPublishStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuName() == null) ? 0 : getSpuName().hashCode());
        result = prime * result + ((getSpuDescription() == null) ? 0 : getSpuDescription().hashCode());
        result = prime * result + ((getCatalogId() == null) ? 0 : getCatalogId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getPublishStatus() == null) ? 0 : getPublishStatus().hashCode());
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
        sb.append(", spuName=").append(spuName);
        sb.append(", spuDescription=").append(spuDescription);
        sb.append(", catalogId=").append(catalogId);
        sb.append(", brandId=").append(brandId);
        sb.append(", weight=").append(weight);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}