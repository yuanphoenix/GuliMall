package com.atguigu.gulimall.gulimallcart.filter;

import constant.LoginConstant;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginFilter implements Filter {


  @Autowired
  private StringRedisTemplate redisTemplate;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    var request = (HttpServletRequest) servletRequest;
    var response = (HttpServletResponse) servletResponse;
    HttpSession session = request.getSession();

    if (session.getAttribute(LoginConstant.LOGIN.getValue()) == null) {
      var key = UUID.randomUUID().toString();
      String backUrl = "http://cart.gulimall.com" + request.getRequestURI();
      if (request.getQueryString() != null) {
        backUrl += "?" + request.getQueryString();
      }
      redisTemplate.opsForValue()
          .set(LoginConstant.BACK_URL.getValue() + key, backUrl,
              30,
              TimeUnit.MINUTES);
      response.sendRedirect("http://auth.gulimall.com?state=" + key);
      return;
    }
    filterChain.doFilter(servletRequest, response);
  }
}
