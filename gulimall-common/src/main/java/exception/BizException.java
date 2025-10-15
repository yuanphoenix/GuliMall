package exception;

/**
 * 自定义的万能异常类
 * @author tifa
 */
public class BizException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final Integer code;

  public BizException(BizCodeEnum bizCodeEnum) {
    super(bizCodeEnum.getMsg());
    this.code = bizCodeEnum.getCode();
  }

  public BizException(BizCodeEnum bizCodeEnum, String msg) {
    super(msg);
    this.code = bizCodeEnum.getCode();
  }

  public Integer getCode() {
    return code;
  }
}
