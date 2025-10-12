package com.atguigu.gulimall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "gulimall.thread")
@Component
public class ThreadPoolConfigProperties {
  private Integer corePoolSize;
  private Integer maxPoolSize;
  private Integer keepAliveTime;


  public Integer getCorePoolSize() {
    return corePoolSize;
  }

  public void setCorePoolSize(Integer corePoolSize) {
    this.corePoolSize = corePoolSize;
  }

  public Integer getMaxPoolSize() {
    return maxPoolSize;
  }

  public void setMaxPoolSize(Integer maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
  }

  public Integer getKeepAliveTime() {
    return keepAliveTime;
  }

  public void setKeepAliveTime(Integer keepAliveTime) {
    this.keepAliveTime = keepAliveTime;
  }
}
