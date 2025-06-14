/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.common.xss;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * XSS过滤处理
 *
 * @author Mark sunlightcs@gmail.com
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

  //html过滤
  private final static HTMLFilter htmlFilter = new HTMLFilter();
  //没被包装过的HttpServletRequest（特殊场景，需要自己过滤）
  HttpServletRequest orgRequest;

  public XssHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);
    orgRequest = request;
  }

  /**
   * 获取最原始的request
   */
  public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
    if (request instanceof XssHttpServletRequestWrapper) {
      return ((XssHttpServletRequestWrapper) request).getOrgRequest();
    }

    return request;
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    //非json类型，直接返回
    if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(
        super.getHeader(HttpHeaders.CONTENT_TYPE))) {
      return super.getInputStream();
    }

    //为空，直接返回
    String json = IOUtils.toString(super.getInputStream(), "utf-8");
    if (StringUtils.isBlank(json)) {
      return super.getInputStream();
    }

    //xss过滤
    json = xssEncode(json);
    final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes("utf-8"));
    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return true;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }

      @Override
      public int read() throws IOException {
        return bis.read();
      }
    };
  }

  @Override
  public String getParameter(String name) {
    String value = super.getParameter(xssEncode(name));
    if (StringUtils.isNotBlank(value)) {
      value = xssEncode(value);
    }
    return value;
  }

  @Override
  public String[] getParameterValues(String name) {
    String[] parameters = super.getParameterValues(name);
    if (parameters == null || parameters.length == 0) {
      return null;
    }

    for (int i = 0; i < parameters.length; i++) {
      parameters[i] = xssEncode(parameters[i]);
    }
    return parameters;
  }

  @Override
  public Map<String, String[]> getParameterMap() {
    Map<String, String[]> map = new LinkedHashMap<>();
    Map<String, String[]> parameters = super.getParameterMap();
    for (String key : parameters.keySet()) {
      String[] values = parameters.get(key);
      for (int i = 0; i < values.length; i++) {
        values[i] = xssEncode(values[i]);
      }
      map.put(key, values);
    }
    return map;
  }

  @Override
  public String getHeader(String name) {
    String value = super.getHeader(xssEncode(name));
    if (StringUtils.isNotBlank(value)) {
      value = xssEncode(value);
    }
    return value;
  }

  private String xssEncode(String input) {
    return htmlFilter.filter(input);
  }

  /**
   * 获取最原始的request
   */
  public HttpServletRequest getOrgRequest() {
    return orgRequest;
  }

}
