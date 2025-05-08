package com.atguigu.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * 成长值变化历史记录
 * @TableName ums_growth_change_history
 */
@TableName(value ="ums_growth_change_history")
public class GrowthChangeHistoryEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * member_id
     */
    private Long memberId;

    /**
     * create_time
     */
    private LocalDateTime createTime;

    /**
     * 改变的值（正负计数）
     */
    private Integer changeCount;

    /**
     * 备注
     */
    private String note;

    /**
     * 积分来源[0-购物，1-管理员修改]
     */
    private Integer sourceType;

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
     * member_id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * member_id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * create_time
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * create_time
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 改变的值（正负计数）
     */
    public Integer getChangeCount() {
        return changeCount;
    }

    /**
     * 改变的值（正负计数）
     */
    public void setChangeCount(Integer changeCount) {
        this.changeCount = changeCount;
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

    /**
     * 积分来源[0-购物，1-管理员修改]
     */
    public Integer getSourceType() {
        return sourceType;
    }

    /**
     * 积分来源[0-购物，1-管理员修改]
     */
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
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
        GrowthChangeHistoryEntity other = (GrowthChangeHistoryEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getChangeCount() == null ? other.getChangeCount() == null : this.getChangeCount().equals(other.getChangeCount()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getChangeCount() == null) ? 0 : getChangeCount().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", createTime=").append(createTime);
        sb.append(", changeCount=").append(changeCount);
        sb.append(", note=").append(note);
        sb.append(", sourceType=").append(sourceType);
        sb.append("]");
        return sb.toString();
    }
}