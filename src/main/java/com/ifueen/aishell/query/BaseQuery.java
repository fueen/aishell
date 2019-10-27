package com.ifueen.aishell.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseQuery {
    //条件
    //所有的子类都必须实现这个抽象方法
    public abstract Specification createSpec();

    //创建排序的对象
    public Sort createSort(){

        if (StringUtils.isNotBlank(orderName)){
            //通过三目运算符返回sort对象
            Sort sort = new Sort(orderType ? Sort.Direction.DESC : Sort.Direction.ASC, orderName);
            return sort;
        }
        return null;
    }

    //当前页数
    private int currentPage = 1;
    //每页显示记录数
    private int pageSize = 10;
    //排序 如果为true，变为升序，false为降序
    private boolean orderType;
    //排序的字段，如果这个字段为null就代表不排序
    private String orderName;

    public int getCurrentPage() {
        //因为用户从1输入,然而JPA却是从0开始计算
        return currentPage-1;
    }

    //和Easui匹配页数
    public void setPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    //和Easyui匹配每页记录数
    public void setRows(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isOrderType() {
        return orderType;
    }

    public void setOrderType(boolean orderType) {
        this.orderType = orderType;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
