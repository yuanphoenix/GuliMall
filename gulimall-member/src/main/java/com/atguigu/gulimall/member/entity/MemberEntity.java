package com.atguigu.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;

/**
 * 会员
 *
 * @TableName ums_member
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "ums_member")
public class MemberEntity {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 会员等级id
   */
  private Long levelId;

  /**
   * 用户名
   */
  @NotBlank(message = "用户名必须填写，不能为空")
  private String username;

  /**
   * 密码
   */
  @NotBlank(message = "密码必须填写，不能为空")
  @Length(min = 6, message = "密码长度不能小于6位")
  private String password;

  /**
   * 昵称
   */
  private String nickname;

  /**
   * 手机号码
   */
  @NotBlank(message = "手机号不能为空")
  private String mobile;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 头像
   */
  private String header;

  /**
   * 性别
   */
  private Integer gender;

  /**
   * 生日
   */
  private LocalDate birth;

  /**
   * 所在城市
   */
  private String city;

  /**
   * 职业
   */
  private String job;

  /**
   * 个性签名
   */
  private String sign;

  /**
   * 用户来源
   */
  private Integer sourceType;

  /**
   * 积分
   */
  private Integer integration;

  /**
   * 成长值
   */
  private Integer growth;

  /**
   * 启用状态
   */
  private Integer status;

  /**
   * 注册时间
   */
  private LocalDateTime createTime;

  /**
   * 微博社交账户uid（应该建立关联表）
   */
  private String weiboUid;

  /**
   * 访问令牌
   */
  private String accessToken;

  /**
   * 访问令牌的过期时间
   */
  private String expiresIn;

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
   * 会员等级id
   */
  public Long getLevelId() {
    return levelId;
  }

  /**
   * 会员等级id
   */
  public void setLevelId(Long levelId) {
    this.levelId = levelId;
  }

  /**
   * 用户名
   */
  public String getUsername() {
    return username;
  }

  /**
   * 用户名
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * 密码
   */
  public String getPassword() {
    return password;
  }

  /**
   * 密码
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 昵称
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * 昵称
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * 手机号码
   */
  public String getMobile() {
    return mobile;
  }

  /**
   * 手机号码
   */
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  /**
   * 邮箱
   */
  public String getEmail() {
    return email;
  }

  /**
   * 邮箱
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * 头像
   */
  public String getHeader() {
    return header;
  }

  /**
   * 头像
   */
  public void setHeader(String header) {
    this.header = header;
  }

  /**
   * 性别
   */
  public Integer getGender() {
    return gender;
  }

  /**
   * 性别
   */
  public void setGender(Integer gender) {
    this.gender = gender;
  }

  /**
   * 生日
   */
  public LocalDate getBirth() {
    return birth;
  }

  /**
   * 生日
   */
  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }

  /**
   * 所在城市
   */
  public String getCity() {
    return city;
  }

  /**
   * 所在城市
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * 职业
   */
  public String getJob() {
    return job;
  }

  /**
   * 职业
   */
  public void setJob(String job) {
    this.job = job;
  }

  /**
   * 个性签名
   */
  public String getSign() {
    return sign;
  }

  /**
   * 个性签名
   */
  public void setSign(String sign) {
    this.sign = sign;
  }

  /**
   * 用户来源
   */
  public Integer getSourceType() {
    return sourceType;
  }

  /**
   * 用户来源
   */
  public void setSourceType(Integer sourceType) {
    this.sourceType = sourceType;
  }

  /**
   * 积分
   */
  public Integer getIntegration() {
    return integration;
  }

  /**
   * 积分
   */
  public void setIntegration(Integer integration) {
    this.integration = integration;
  }

  /**
   * 成长值
   */
  public Integer getGrowth() {
    return growth;
  }

  /**
   * 成长值
   */
  public void setGrowth(Integer growth) {
    this.growth = growth;
  }

  /**
   * 启用状态
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 启用状态
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * 注册时间
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * 注册时间
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  /**
   * 微博社交账户uid（应该建立关联表）
   */
  public String getWeiboUid() {
    return weiboUid;
  }

  /**
   * 微博社交账户uid（应该建立关联表）
   */
  public void setWeiboUid(String weiboUid) {
    this.weiboUid = weiboUid;
  }

  /**
   * 访问令牌
   */
  public String getAccessToken() {
    return accessToken;
  }

  /**
   * 访问令牌
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  /**
   * 访问令牌的过期时间
   */
  public String getExpiresIn() {
    return expiresIn;
  }

  /**
   * 访问令牌的过期时间
   */
  public void setExpiresIn(String expiresIn) {
    this.expiresIn = expiresIn;
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
    MemberEntity other = (MemberEntity) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getLevelId() == null ? other.getLevelId() == null
        : this.getLevelId().equals(other.getLevelId()))
        && (this.getUsername() == null ? other.getUsername() == null
        : this.getUsername().equals(other.getUsername()))
        && (this.getPassword() == null ? other.getPassword() == null
        : this.getPassword().equals(other.getPassword()))
        && (this.getNickname() == null ? other.getNickname() == null
        : this.getNickname().equals(other.getNickname()))
        && (this.getMobile() == null ? other.getMobile() == null
        : this.getMobile().equals(other.getMobile()))
        && (this.getEmail() == null ? other.getEmail() == null
        : this.getEmail().equals(other.getEmail()))
        && (this.getHeader() == null ? other.getHeader() == null
        : this.getHeader().equals(other.getHeader()))
        && (this.getGender() == null ? other.getGender() == null
        : this.getGender().equals(other.getGender()))
        && (this.getBirth() == null ? other.getBirth() == null
        : this.getBirth().equals(other.getBirth()))
        && (this.getCity() == null ? other.getCity() == null
        : this.getCity().equals(other.getCity()))
        && (this.getJob() == null ? other.getJob() == null : this.getJob().equals(other.getJob()))
        && (this.getSign() == null ? other.getSign() == null
        : this.getSign().equals(other.getSign()))
        && (this.getSourceType() == null ? other.getSourceType() == null
        : this.getSourceType().equals(other.getSourceType()))
        && (this.getIntegration() == null ? other.getIntegration() == null
        : this.getIntegration().equals(other.getIntegration()))
        && (this.getGrowth() == null ? other.getGrowth() == null
        : this.getGrowth().equals(other.getGrowth()))
        && (this.getStatus() == null ? other.getStatus() == null
        : this.getStatus().equals(other.getStatus()))
        && (this.getCreateTime() == null ? other.getCreateTime() == null
        : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getWeiboUid() == null ? other.getWeiboUid() == null
        : this.getWeiboUid().equals(other.getWeiboUid()))
        && (this.getAccessToken() == null ? other.getAccessToken() == null
        : this.getAccessToken().equals(other.getAccessToken()))
        && (this.getExpiresIn() == null ? other.getExpiresIn() == null
        : this.getExpiresIn().equals(other.getExpiresIn()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getLevelId() == null) ? 0 : getLevelId().hashCode());
    result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
    result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
    result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
    result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
    result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
    result = prime * result + ((getHeader() == null) ? 0 : getHeader().hashCode());
    result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
    result = prime * result + ((getBirth() == null) ? 0 : getBirth().hashCode());
    result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
    result = prime * result + ((getJob() == null) ? 0 : getJob().hashCode());
    result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
    result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
    result = prime * result + ((getIntegration() == null) ? 0 : getIntegration().hashCode());
    result = prime * result + ((getGrowth() == null) ? 0 : getGrowth().hashCode());
    result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getWeiboUid() == null) ? 0 : getWeiboUid().hashCode());
    result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());
    result = prime * result + ((getExpiresIn() == null) ? 0 : getExpiresIn().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", levelId=").append(levelId);
    sb.append(", username=").append(username);
    sb.append(", password=").append(password);
    sb.append(", nickname=").append(nickname);
    sb.append(", mobile=").append(mobile);
    sb.append(", email=").append(email);
    sb.append(", header=").append(header);
    sb.append(", gender=").append(gender);
    sb.append(", birth=").append(birth);
    sb.append(", city=").append(city);
    sb.append(", job=").append(job);
    sb.append(", sign=").append(sign);
    sb.append(", sourceType=").append(sourceType);
    sb.append(", integration=").append(integration);
    sb.append(", growth=").append(growth);
    sb.append(", status=").append(status);
    sb.append(", createTime=").append(createTime);
    sb.append(", weiboUid=").append(weiboUid);
    sb.append(", accessToken=").append(accessToken);
    sb.append(", expiresIn=").append(expiresIn);
    sb.append("]");
    return sb.toString();
  }
}