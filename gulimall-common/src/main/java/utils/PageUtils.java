package utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtils<T> {

  private IPage<T> page;


  public PageUtils() {

  }

  public PageUtils(IPage<T> page) {
    this.page = page;
  }


  public static <T> IPage<T> of(PageDTO pageDTO) {
    return Page.of(pageDTO.getPage(), pageDTO.getLimit(), true);
  }

  public <AnotherType> Page<AnotherType> convertTo(Function<? super T, ? extends AnotherType> mapper) {
    List<T> records = this.page.getRecords();
    List<AnotherType> list = records.stream().map(mapper).collect(Collectors.toList());
    Page<AnotherType> rPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
    rPage.setRecords(list);
    return rPage;
  }

}
