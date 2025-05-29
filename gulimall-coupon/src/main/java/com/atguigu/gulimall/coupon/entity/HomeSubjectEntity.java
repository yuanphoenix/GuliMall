package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @TableName sms_home_subject
 */
@TableName(value = "sms_home_subject")
public class HomeSubjectEntity {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 专题名字
     */
    private String name;

    /**
     * 专题标题
     */
    private String title;

    /**
     * 专题副标题
     */
    private String subTitle;

    /**
     * 显示状态
     */
    private Integer status;

    /**
     * 详情连接
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 专题图片地址
     */
    private String img;

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
     * 专题名字
     */
    public String getName() {
        return name;
    }

    /**
     * 专题名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 专题标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 专题标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 专题副标题
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 专题副标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 显示状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 显示状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 详情连接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 详情连接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 专题图片地址
     */
    public String getImg() {
        return img;
    }

    /**
     * 专题图片地址
     */
    public void setImg(String img) {
        this.img = img;
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
        HomeSubjectEntity other = (HomeSubjectEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getSubTitle() == null ? other.getSubTitle() == null : this.getSubTitle().equals(other.getSubTitle()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSubTitle() == null) ? 0 : getSubTitle().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", status=").append(status);
        sb.append(", url=").append(url);
        sb.append(", sort=").append(sort);
        sb.append(", img=").append(img);
        sb.append("]");
        return sb.toString();
    }
}