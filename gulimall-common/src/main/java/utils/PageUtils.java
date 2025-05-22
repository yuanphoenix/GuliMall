package utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public class PageUtils<T> {

    public Page<T> getPageList(Map<String, Object> params) {
        int current = Integer.parseInt((String) params.get("page"));
        int size = Integer.parseInt((String) params.get("limit"));
        return new Page<T>(current, size);

    }


    public Page<T> getPageList(PageDTO pageDTO) {
        return new Page<T>(pageDTO.getPage(), pageDTO.getLimit());
    }

}
