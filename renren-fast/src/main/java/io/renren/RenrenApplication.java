/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren;

import io.renren.common.annotation.SysLog;
import io.renren.modules.app.dao.UserDao;
import io.renren.modules.job.dao.ScheduleJobDao;
import io.renren.modules.oss.dao.SysOssDao;
import io.renren.modules.sys.dao.SysMenuDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@MapperScan(basePackageClasses = {UserDao.class, ScheduleJobDao.class, SysOssDao.class,
    SysLog.class,
    SysMenuDao.class})
@SpringBootApplication
@EnableDiscoveryClient
public class RenrenApplication {

  public static void main(String[] args) {
    SpringApplication.run(RenrenApplication.class, args);
  }

}