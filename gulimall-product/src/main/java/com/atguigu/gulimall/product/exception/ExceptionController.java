package com.atguigu.gulimall.product.exception;

import exception.BizCodeEnum;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import utils.R;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionController {

  private final static Logger log = LoggerFactory.getLogger(ExceptionController.class);

  /**
   * 处理参数校验异常
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public R handleValidException(MethodArgumentNotValidException e) {
    log.error("数据校验出现异常{}，异常类型{}", e.getMessage(), e.getClass());
    BindingResult bindingResult = e.getBindingResult();
    Map<String, Object> map = new HashMap<>();
    bindingResult.getFieldErrors().forEach((fieldError -> {
      map.put(fieldError.getField(), fieldError.getDefaultMessage());
    }));
    return R.error(BizCodeEnum.VALID_EXCEPTION).put("data", map);
  }

  /**
   * 全局异常兜底处理
   */
  @ExceptionHandler(Throwable.class)
  public R handleException(Throwable throwable) {
    log.error(throwable.getMessage());
    return R.error(BizCodeEnum.UNKONE_EXCEPTION);
  }

}
