package com.atguigu.gulimall.auth.filter;

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
import session.CustomRedisSession;

public class LoginCheckFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    HttpSession session = request.getSession();
    //这里可能有安全隐患
    if (session.getAttribute(LoginConstant.LOGIN.getValue()) != null) {
      response.sendRedirect("http://gulimall.com");
      return;
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
