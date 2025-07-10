package constant;

public class WareConstant {


  public enum PurchaseStatusEnum {
    CREATED(0, "新建"),
    ASSIGNED(1, "已分配"),
    RECEIVE(2, "已领取"),
    BUYING(2, "正在采购"),
    FINISH(3, "已完成"),
    HAVEERROR(4, "有错误");
    private int code;
    private String msg;

    PurchaseStatusEnum(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }
  }
}
