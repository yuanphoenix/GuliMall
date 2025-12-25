package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BizCodeEnum;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.ObjectProvider;

public class R extends HashMap<String, Object> {

  public static R ok() {
    R r = new R();
    r.put("code", 0);
    return r;
  }

  public static R ok(String msg) {
    return ok().put("msg", msg);
  }

  public static R ok(Map<String, Object> map) {
    R c = ok();
    c.putAll(map);
    return c;
  }

  public Integer getCode() {
    return Integer.parseInt(this.getOrDefault("code", -1).toString());
  }

  public static R error() {
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "出错了");
  }

  public static R error(String msg) {
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
  }

  public static R error(int code, String msg) {
    R r = new R();
    r.put("code", code);
    r.put("msg", msg);
    return r;
  }

  public static R error(BizCodeEnum bizCodeEnum) {
    R r = new R();
    r.put("code", bizCodeEnum.getCode());
    r.put("msg", bizCodeEnum.getMsg());
    return r;
  }

  @Override
  public R put(String key, Object value) {
    super.put(key, value);
    return this;
  }

  public <T> T getData(TypeReference<T> typeReference) {
    return new ObjectMapper().convertValue(this.get("data"), typeReference);
  }
}
