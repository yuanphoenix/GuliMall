package utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import exception.BizCodeEnum;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;

public class R extends HashMap<String, Object> {

  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

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

  public <T> T getData(TypeReference<T> t) {
    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

    objectMapper.registerModule(javaTimeModule);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    // 自动注册其他模块，兼容性更好
    objectMapper.findAndRegisterModules();
    return objectMapper.convertValue(this.get("data"), t);
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
}
