package utils;

import java.util.Optional;

public class PageDTO {

    private Integer page;
    private Integer limit;
    private String key;
    private String sidx;
    private String order;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getKey() {
        return Optional.ofNullable(key).orElse("");
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
