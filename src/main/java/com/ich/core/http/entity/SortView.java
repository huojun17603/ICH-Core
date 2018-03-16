package com.ich.core.http.entity;

/**
 * 页面排序参数
 */
public class SortView {

    //排序字段
    private String sort;
    //排序序列 asc desc
    private String order;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
