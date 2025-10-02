package com.atguigu.gulimall.product.app;

import com.atguigu.gulimall.product.entity.CommentReplayEntity;
import com.atguigu.gulimall.product.service.CommentReplayService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.R;

/**
 * <p>
 * 商品评价回复关系 前端控制器
 * </p>
 *
 * @author tifa
 * @since 2025-05-09
 */
@RestController
@RequestMapping("/product/commentReplay")
public class CommentReplayController {

  @Autowired
  private CommentReplayService commentReplayService;

  /**
   * 获取所有数据
   */
  @GetMapping("/list")
  public R list() {
    List<CommentReplayEntity> list = commentReplayService.list();
    return R.ok().put("data", list);
  }

  /**
   * 根据ID获取数据
   */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    CommentReplayEntity entity = commentReplayService.getById(id);
    return R.ok().put("data", entity);
  }

  /**
   * 保存数据
   */
  @PostMapping("/save")
  public R save(@RequestBody CommentReplayEntity commentReplay) {
    boolean saved = commentReplayService.save(commentReplay);
    return saved ? R.ok() : R.error();
  }

  /**
   * 修改数据
   */
  @PostMapping("/update")
  public R update(@RequestBody CommentReplayEntity commentReplay) {
    boolean updated = commentReplayService.updateById(commentReplay);
    return updated ? R.ok() : R.error();
  }

  /**
   * 删除数据
   */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
    boolean removed = commentReplayService.removeById(id);
    return removed ? R.ok() : R.error();
  }
}
