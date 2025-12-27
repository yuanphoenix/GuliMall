package to;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;

/**
 * 会员
 *
 * @TableName ums_member
 */
public class MemberEntityVo {

  /**
   * id
   */
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


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getLevelId() {
    return levelId;
  }

  public void setLevelId(Long levelId) {
    this.levelId = levelId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public LocalDate getBirth() {
    return birth;
  }

  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public Integer getSourceType() {
    return sourceType;
  }

  public void setSourceType(Integer sourceType) {
    this.sourceType = sourceType;
  }

  public Integer getIntegration() {
    return integration;
  }

  public void setIntegration(Integer integration) {
    this.integration = integration;
  }

  public Integer getGrowth() {
    return growth;
  }

  public void setGrowth(Integer growth) {
    this.growth = growth;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public String getWeiboUid() {
    return weiboUid;
  }

  public void setWeiboUid(String weiboUid) {
    this.weiboUid = weiboUid;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(String expiresIn) {
    this.expiresIn = expiresIn;
  }
}