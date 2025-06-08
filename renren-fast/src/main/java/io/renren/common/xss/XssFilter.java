/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.common.xss;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤
 *
 * @author Mark sunlightcs@gmail.com
 */
public class XssFilter implements Filter {

  @Override
  public void init(FilterConfig config) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
        (HttpServletRequest) request);
    chain.doFilter(xssRequest, response);
  }

  @Override
  public void destroy() {
  }

}