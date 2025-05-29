package exception;

public enum BizCodeEnum {
    VALID_EXCEPTION(10001, "参数格式校验失败"),
    UNKONE_EXCEPTION(10000, "系统未知异常");
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
