package ${package.Controller};

import ${package.Entity}.${entity}Entity;
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import java.util.List;

/**
* <p>
  * ${table.comment!''} 前端控制器
  * </p>
*
* @author ${author}
* @since ${date}
*/
@RestController
<#assign packageParts = package.Controller?split(".")>
<#assign moduleName = packageParts[packageParts?size - 2]>
@RequestMapping("/${moduleName}/${table.entityPath}")
public class ${table.controllerName} {

@Autowired
private ${table.serviceName} ${table.entityPath}Service;

/**
* 获取所有数据
*/
@GetMapping("/list")
public R list() {
List
<${entity}Entity> list = ${table.entityPath}Service.list();
  return R.ok().put("data", list);
  }

  /**
  * 根据ID获取数据
  */
  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    ${entity}Entity entity = ${table.entityPath}Service.getById(id);
  return R.ok().put("data", entity);
  }

  /**
  * 保存数据
  */
  @PostMapping("/save")
  public R save(@RequestBody ${entity}Entity ${table.entityPath}) {
  boolean saved = ${table.entityPath}Service.save(${table.entityPath});
  return saved ? R.ok() : R.error();
  }

  /**
  * 修改数据
  */
  @PostMapping("/update")
  public R update(@RequestBody ${entity}Entity ${table.entityPath}) {
  boolean updated = ${table.entityPath}Service.updateById(${table.entityPath});
  return updated ? R.ok() : R.error();
  }

  /**
  * 删除数据
  */
  @PostMapping("/delete/{id}")
  public R delete(@PathVariable("id") Long id) {
  boolean removed = ${table.entityPath}Service.removeById(id);
  return removed ? R.ok() : R.error();
  }
  }
