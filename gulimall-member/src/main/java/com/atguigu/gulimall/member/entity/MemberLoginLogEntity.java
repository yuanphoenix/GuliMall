package com.atguigu.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 会员登录记录
 *
 * @TableName ums_member_login_log
 */
@TableName(value = "ums_member_login_log")
public class MemberLoginLogEntity {
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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * ip
     */
    private String ip;

    /**
     * city
     */
    private String city;

    /**
     * 登录类型[1-web，2-app]
     */
    private Integer loginType;

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
     * 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * city
     */
    public String getCity() {
        return city;
    }

    /**
     * city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 登录类型[1-web，2-app]
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * 登录类型[1-web，2-app]
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
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
        MemberLoginLogEntity other = (MemberLoginLogEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
                && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
                && (this.getLoginType() == null ? other.getLoginType() == null : this.getLoginType().equals(other.getLoginType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getLoginType() == null) ? 0 : getLoginType().hashCode());
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
        sb.append(", ip=").append(ip);
        sb.append(", city=").append(city);
        sb.append(", loginType=").append(loginType);
        sb.append("]");
        return sb.toString();
    }
}