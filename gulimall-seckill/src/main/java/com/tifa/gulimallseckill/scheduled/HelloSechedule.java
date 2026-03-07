package com.tifa.gulimallseckill.scheduled;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloSechedule {

  @XxlJob("demoJobHandler")
  public void demoJob() throws Exception {
    log.info("XXL-JOB 执行了: {}", System.currentTimeMillis());
  }
}
