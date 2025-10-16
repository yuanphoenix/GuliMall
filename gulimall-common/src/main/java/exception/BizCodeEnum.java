package exception;

/**
 * @author tifa
 */

public enum BizCodeEnum {
  VALID_EXCEPTION(10001, "参数格式校验失败"),
  UNKONE_EXCEPTION(10000, "系统未知异常"),
  AUTH_SAVE_EXCEPTION(10005, "认证保存异常"),
  CODE_TIME_EXCEPTION(10003, "验证码还在有效期"),
  CODE_EXCEPTION(10004, "验证码错误"),
  STYLE_EXCEPTION(10002, "格式错误");

  private Integer code;
  private String msg;

  BizCodeEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
