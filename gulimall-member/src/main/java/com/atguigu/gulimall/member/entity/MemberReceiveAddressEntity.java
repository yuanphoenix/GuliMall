package com.atguigu.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 会员收货地址
 *
 * @TableName ums_member_receive_address
 */
@TableName(value = "ums_member_receive_address")
public class MemberReceiveAddressEntity {
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
     * 收货人姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 省份/直辖市
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区
     */
    private String region;

    /**
     * 详细地址(街道)
     */
    private String detailAddress;

    /**
     * 省市区代码
     */
    private String areacode;

    /**
     * 是否默认
     */
    private Integer defaultStatus;

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
     * 收货人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 收货人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 邮政编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 邮政编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 省份/直辖市
     */
    public String getProvince() {
        return province;
    }

    /**
     * 省份/直辖市
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 区
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 详细地址(街道)
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 详细地址(街道)
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    /**
     * 省市区代码
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     * 省市区代码
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    /**
     * 是否默认
     */
    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    /**
     * 是否默认
     */
    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
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
        MemberReceiveAddressEntity other = (MemberReceiveAddressEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getPostCode() == null ? other.getPostCode() == null : this.getPostCode().equals(other.getPostCode()))
                && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
                && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
                && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
                && (this.getDetailAddress() == null ? other.getDetailAddress() == null : this.getDetailAddress().equals(other.getDetailAddress()))
                && (this.getAreacode() == null ? other.getAreacode() == null : this.getAreacode().equals(other.getAreacode()))
                && (this.getDefaultStatus() == null ? other.getDefaultStatus() == null : this.getDefaultStatus().equals(other.getDefaultStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPostCode() == null) ? 0 : getPostCode().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getDetailAddress() == null) ? 0 : getDetailAddress().hashCode());
        result = prime * result + ((getAreacode() == null) ? 0 : getAreacode().hashCode());
        result = prime * result + ((getDefaultStatus() == null) ? 0 : getDefaultStatus().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", postCode=").append(postCode);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", region=").append(region);
        sb.append(", detailAddress=").append(detailAddress);
        sb.append(", areacode=").append(areacode);
        sb.append(", defaultStatus=").append(defaultStatus);
        sb.append("]");
        return sb.toString();
    }
}