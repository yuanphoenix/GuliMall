package com.atguigu.gulimall.gulimallcart.config;

import com.atguigu.gulimall.gulimallcart.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  /**
   * 购物车不允许不登录访问
   * @return
   */
  @Bean
  public FilterRegistrationBean<LoginFilter> loginCheck(LoginFilter loginFilter) {
    var register = new FilterRegistrationBean<LoginFilter>();
    register.setFilter(loginFilter);
    register.addUrlPatterns("/*");
    register.setOrder(1);
    return register;
  }

}
