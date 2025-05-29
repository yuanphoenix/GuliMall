package com.atguigu.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 积分变化历史记录
 *
 * @TableName ums_integration_change_history
 */
@TableName(value = "ums_integration_change_history")
public class IntegrationChangeHistoryEntity {
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
     * 变化的值
     */
    private Integer changeCount;

    /**
     * 备注
     */
    private String note;

    /**
     * 来源[0->购物；1->管理员修改;2->活动]
     */
    private Integer sourceTyoe;

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
     * 变化的值
     */
    public Integer getChangeCount() {
        return changeCount;
    }

    /**
     * 变化的值
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
     * 来源[0->购物；1->管理员修改;2->活动]
     */
    public Integer getSourceTyoe() {
        return sourceTyoe;
    }

    /**
     * 来源[0->购物；1->管理员修改;2->活动]
     */
    public void setSourceTyoe(Integer sourceTyoe) {
        this.sourceTyoe = sourceTyoe;
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
        IntegrationChangeHistoryEntity other = (IntegrationChangeHistoryEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getChangeCount() == null ? other.getChangeCount() == null : this.getChangeCount().equals(other.getChangeCount()))
                && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
                && (this.getSourceTyoe() == null ? other.getSourceTyoe() == null : this.getSourceTyoe().equals(other.getSourceTyoe()));
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
        result = prime * result + ((getSourceTyoe() == null) ? 0 : getSourceTyoe().hashCode());
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
        sb.append(", sourceTyoe=").append(sourceTyoe);
        sb.append("]");
        return sb.toString();
    }
}