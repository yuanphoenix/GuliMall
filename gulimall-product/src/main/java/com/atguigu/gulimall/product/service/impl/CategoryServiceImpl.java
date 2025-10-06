package com.atguigu.gulimall.product.service.impl;


import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.mapper.CategoryMapper;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import com.atguigu.gulimall.product.vo.Catelog2Vo.Catelog3Vo;
import com.atguigu.gulimall.product.vo.TreeVoRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import utils.JsonUtils;

/**
 * @author tifa
 * @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
 * @createDate 2025-05-08 20:51:50
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService {

  private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
  private final StringRedisTemplate redisTemplate;
  private final RedissonClient redissonClient;

  public CategoryServiceImpl(StringRedisTemplate redisTemplate, RedissonClient redissonClient) {
    this.redisTemplate = redisTemplate;
    this.redissonClient = redissonClient;
  }

  @Override
  public List<CategoryEntity> listWithTree() {
    String s = redisTemplate.opsForValue().get("categoryTree");
    if (StringUtils.hasText(s)) {
      return JsonUtils.convertJson2Object(s, new TypeReference<>() {
      });
    }

    List<CategoryEntity> list = baseMapper.selectList(null);
    list.forEach(c -> c.setSort(c.getSort() == null ? 0 : c.getSort()));
    list.sort(Comparator.comparingInt(CategoryEntity::getSort));
    HashMap<Long, CategoryEntity> categoryMap = new HashMap<>();
    for (CategoryEntity category : list) {
      categoryMap.put(category.getCatId(), category);
    }
    for (CategoryEntity category : list) {
      if (!categoryMap.containsKey(category.getParentCid())) {
        continue;
      }
      categoryMap.get(category.getParentCid()).getChildren().add(category);
      categoryMap.get(category.getParentCid()).setLeaf(Boolean.FALSE);
    }

    List<CategoryEntity> result = list.stream().filter(e -> e.getCatLevel() == 1).toList();
    redisTemplate.opsForValue()
        .set("categoryTree", JsonUtils.convertObject2Json(result), Duration.ofHours(1));
    return result;
  }

  @Override
  public boolean checkAndRemove(CategoryEntity category) {
    if (category == null || category.getCatId() == null) {
      return true;
    }

    CategoryEntity byId = this.getById(category.getCatId());
    if (byId == null || byId.getCatId() == null) {
      return true;
    }
    if (!baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", byId.getCatId()))
        .isEmpty()) {
      return false;
    }

    return baseMapper.deleteById(byId.getCatId()) == 1;
  }

  @Override
  public boolean addNew(CategoryEntity category) {
    if (category == null || category.getParentCid() == null) {
      return false;
    }
    CategoryEntity categoryEntity = baseMapper.selectById(category.getParentCid());
    if (categoryEntity == null) {
      return false;
    }
    String categoryName = category.getName();
    BeanUtils.copyProperties(categoryEntity, category);
    category.setCatLevel(categoryEntity.getCatLevel() + 1);
    category.setName(categoryName);
    category.setParentCid(categoryEntity.getCatId());
    category.setCatId(null);
    category.setSort(-1);
    return baseMapper.insert(category) == 1;
  }

  @Override
  public boolean sort(TreeVoRequest treeVoRequest) {
    if (treeVoRequest == null) {
      return false;
    }
    Long target = treeVoRequest.getDropNodeId();
    Long moveId = treeVoRequest.getDraggingNodeId();
    String type = treeVoRequest.getDropType();
    if (target == null || moveId == null || type == null) {
      return false;
    }
    CategoryEntity targetEntity = baseMapper.selectById(target);
    CategoryEntity moveEntity = baseMapper.selectById(moveId);

    if ("inner".equals(type)) {
      moveEntity.setParentCid(targetEntity.getCatId());
      moveEntity.setCatLevel(targetEntity.getCatLevel() + 1);
      moveEntity.setSort(Integer.MIN_VALUE);
      List<CategoryEntity> parentCid = baseMapper.selectList(
          new QueryWrapper<CategoryEntity>().eq("parent_cid", targetEntity.getCatId()));
      parentCid = new ArrayList<>(
          parentCid.stream().filter(a -> a.getCatId().equals(moveEntity.getCatId())).toList());
      parentCid.add(moveEntity);
      parentCid.sort(Comparator.comparingInt(CategoryEntity::getSort));
      for (int i = 0; i < parentCid.size(); i++) {
        parentCid.get(i).setSort(i);
      }
      this.saveOrUpdateBatch(parentCid);
    } else {
      moveEntity.setParentCid(targetEntity.getParentCid());
      moveEntity.setCatLevel(targetEntity.getCatLevel());
      List<CategoryEntity> parentCid = baseMapper.selectList(
          new QueryWrapper<CategoryEntity>().eq("parent_cid", targetEntity.getParentCid()));
      parentCid.sort(Comparator.comparingInt(CategoryEntity::getSort));
      List<CategoryEntity> temp = new ArrayList<>(parentCid.size() + 1);
      for (var entity : parentCid) {
        if (entity.getCatId().equals(moveEntity.getCatId())) {
          continue;
        }
        if (entity.getCatId().equals(target)) {
          if ("before".equals(type)) {
            temp.add(moveEntity);
            temp.add(entity);
            continue;
          } else {
            temp.add(entity);
            temp.add(moveEntity);
            continue;
          }
        }
        temp.add(entity);
      }
      for (int i = 0; i < temp.size(); i++) {
        temp.get(i).setSort(i);
      }
      this.saveOrUpdateBatch(temp);
    }
    updateChildren(moveEntity);
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean removeBatchByEntities(List<CategoryEntity> categoryEntityList) {
    if (categoryEntityList == null) {
      return false;
    }
    return baseMapper.deleteByIds(
        categoryEntityList.stream().map(CategoryEntity::getCatId).filter(Objects::nonNull).toList())
        >= 0;

  }

  @Cacheable(value = "#root.methodName", key = "'level1'")
  @Override
  public List<CategoryEntity> selectLevelOneCategorys() {
    return this.baseMapper.selectList(
        new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentCid, 0)
            .select(CategoryEntity::getCatId, CategoryEntity::getName));
  }

  /**
   * 使用redisson来实现分布式锁
   *
   * @return
   */
  @Override
  public Map<String, List<Catelog2Vo>> getCatalogJson() {
    String catalogJson = redisTemplate.opsForValue().get("catalogJson");
    if (StringUtils.hasText(catalogJson)) {
      return JsonUtils.convertJson2Object(catalogJson, new TypeReference<>() {
      });
    }

    RLock catalock = redissonClient.getLock("catalogJsonLock");

    try {
      boolean b = catalock.tryLock(30, 10, TimeUnit.SECONDS);
      if (!b) {
        return getCatalogJsonFromDB();
      }
      catalogJson = redisTemplate.opsForValue().get("catalogJson");

      if (StringUtils.hasText(catalogJson)) {
        return JsonUtils.convertJson2Object(catalogJson, new TypeReference<>() {
        });
      }
      Map<String, List<Catelog2Vo>> catalogJsonFromDB = getCatalogJsonFromDB();
      redisTemplate.opsForValue()
          .set("catalogJson", JsonUtils.convertObject2Json(catalogJsonFromDB), Duration.ofHours(1));
      return catalogJsonFromDB;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      if (catalock.isHeldByCurrentThread()) {
        catalock.unlock();
      }
    }
  }

  //
//  @Override

  //使用redis实现锁

//  public Map<String, List<Catelog2Vo>> getCatalogJson() {
//    /**
//     * 为了跨平台，缓存中都保存JSON数据。
//     */
//
//    while (true) {
//      String catalogJson = redisTemplate.opsForValue().get("catalogJson");
//
//      if (!StringUtils.hasText(catalogJson)) {
//        return JsonUtils.convertJson2Object(catalogJson, new TypeReference<>() {
//        });
//      }
//
//      String uuid = UUID.randomUUID().toString();
//      Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, Duration.ofSeconds(300));
//      if (Boolean.TRUE.equals(lock)) {
//        Map<String, List<Catelog2Vo>> catalogJsonFromDB = getCatalogJsonFromDB();
//        String json = JsonUtils.convertObject2Json(catalogJsonFromDB);
//        redisTemplate.opsForValue().set("catalogJson", json, Duration.ofHours(1));
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then\n"
//            + "    return redis.call('del', KEYS[1])\n"
//            + "else\n"
//            + "    return 0\n"
//            + "end\n";
//        Long lock1 = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
//            List.of("lock"),
//            uuid);
//        logger.warn(lock1 + "");
//        return catalogJsonFromDB;
//      } else {
//        try {
//          Thread.sleep(50);
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
//      }
//    }
//  }


  /**
   * 从数据库查询分类数据
   *
   * @return
   */
  public Map<String, List<Catelog2Vo>> getCatalogJsonFromDB() {

    List<CategoryEntity> categoryEntities = this.baseMapper.selectList(
        new LambdaQueryWrapper<CategoryEntity>().select(CategoryEntity::getCatId,
            CategoryEntity::getName,
            CategoryEntity::getParentCid, CategoryEntity::getCatLevel));
    //构建第一层
    Map<String, List<Catelog2Vo>> result = categoryEntities.stream()
        .filter(a -> a.getCatLevel() == 1).map(CategoryEntity::getCatId)
        .collect(
            Collectors.toMap(String::valueOf, a -> new ArrayList<Catelog2Vo>()));

    var level2Map = categoryEntities.stream().filter(a -> a.getCatLevel() == 3).map(a -> {
      Catelog3Vo catelog3Vo = new Catelog3Vo();
      catelog3Vo.setId(String.valueOf(a.getCatId()));
      catelog3Vo.setName(a.getName());
      catelog3Vo.setCatalog2Id(String.valueOf(a.getParentCid()));
      return catelog3Vo;
    }).collect(Collectors.groupingBy(Catelog3Vo::getCatalog2Id));

    categoryEntities.stream().filter(a -> a.getCatLevel() == 2).forEach(a -> {
      Catelog2Vo catelog2Vo = new Catelog2Vo();
      catelog2Vo.setCatalog1Id(String.valueOf(a.getParentCid()));
      catelog2Vo.setId(String.valueOf(a.getCatId()));
      catelog2Vo.setName(a.getName());
      catelog2Vo.setCatalog3List(level2Map.getOrDefault(catelog2Vo.getId(), List.of()));
      result.get(catelog2Vo.getCatalog1Id()).add(catelog2Vo);
    });

    return result;
  }


  private void updateChildren(CategoryEntity category) {
    //更新moveEntity所有子代的catLevel
    LinkedList<CategoryEntity> list = new LinkedList<>();
    list.add(category);
    while (!list.isEmpty()) {
      CategoryEntity categoryEntity = list.removeFirst();
      List<CategoryEntity> parentCid = baseMapper.selectList(
          new QueryWrapper<CategoryEntity>().eq("parent_cid", categoryEntity.getCatId()));
      list.addAll(parentCid);
      for (CategoryEntity parentCategoryEntity : parentCid) {
        parentCategoryEntity.setCatLevel(categoryEntity.getCatLevel() + 1);
      }
      this.saveOrUpdateBatch(parentCid);
    }
  }

}




