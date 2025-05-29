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
        return new Page<>(pageDTO.getPage(), pageDTO.getLimit());
    }

    public <E> Page<E> convertTo(Function<? super T, ? extends E> mapper) {
        List<T> records = this.page.getRecords();
        List<E> list = records.stream().map(mapper).collect(Collectors.toList());
        Page<E> rPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        rPage.setRecords(list);
        return rPage;
    }

}
