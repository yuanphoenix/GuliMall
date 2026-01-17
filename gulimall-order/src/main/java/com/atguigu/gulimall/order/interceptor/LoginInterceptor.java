package com.atguigu.gulimall.order.interceptor;

import constant.LoginConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {


  @Autowired
  private StringRedisTemplate redisTemplate;


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute(LoginConstant.LOGIN.getValue()) == null) {
      var key = UUID.randomUUID().toString();
      String backUrl = "http://order.gulimall.com" + request.getRequestURI();
      if (request.getQueryString() != null) {
        backUrl += "?" + request.getQueryString();
      }
      redisTemplate.opsForValue()
          .set(LoginConstant.BACK_URL.getValue() + key, backUrl,
              30,
              TimeUnit.MINUTES);
      response.sendRedirect("http://auth.gulimall.com?state=" + key);
      return false;
    }
    return true;
  }
}
