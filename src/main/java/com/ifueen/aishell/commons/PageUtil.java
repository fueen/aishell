package com.ifueen.aishell.commons;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageUtil {

    private long total;
    private List rows;

    public PageUtil(Page page) {
        total = page.getTotalElements();
        rows = page.getContent();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
