package com.atguigu.gulimall.auth.config;

import com.atguigu.gulimall.auth.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean<LoginCheckFilter> loginCheckFilterRegistrationBean() {
    FilterRegistrationBean<LoginCheckFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new LoginCheckFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    //值越小，优先级越高
    filterRegistrationBean.setOrder(1);
    return filterRegistrationBean;
  }

}
