package com.atguigu.gulimall.auth.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * 处理参数校验异常
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ModelAndView handleValidException(MethodArgumentNotValidException e,
      HttpServletRequest request) {
    log.error("数据校验出现异常{}，异常类型{}", e.getMessage(), e.getClass());
    String requestUri = request.getRequestURI();
    String[] split = requestUri.split("/");
    String viewName = split[split.length - 1];
    Map<String, String> errorMap = new HashMap<>(10);
    e.getBindingResult().getFieldErrors()
        .forEach(err -> errorMap.put(err.getField(), err.getDefaultMessage()));
    //model数据{password=密码必须填写, code=验证码必须填写, phone=手机号必须填写, userName=用户名必须提交}
    log.info("model数据{}", errorMap);
    ModelAndView mv = new ModelAndView(viewName);
    mv.addObject("errors", errorMap);
    return mv;
  }

  /**
   * 全局异常兜底处理
   */
  @ExceptionHandler(Throwable.class)
  public ModelAndView handleException(Throwable throwable, HttpServletRequest request) {
    String requestUri = request.getRequestURI();
    String[] split = requestUri.split("/");
    String viewName = split[split.length - 1];
    ModelAndView mv = new ModelAndView(viewName);
    mv.addObject("errors");
    return mv;
  }

}
