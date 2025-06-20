/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysMenuEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

  /**
   * 根据父菜单，查询子菜单
   * @param parentId 父菜单ID
   */
  List<SysMenuEntity> queryListParentId(Long parentId);

  /**
   * 获取不包含按钮的菜单列表
   */
  List<SysMenuEntity> queryNotButtonList();

}
