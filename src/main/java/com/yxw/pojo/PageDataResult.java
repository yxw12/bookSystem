package com.yxw.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yan on 2018/8/30.
 */
public class PageDataResult implements Serializable {
    private Integer total;

    private List<BookInfo> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<BookInfo> getRows() {
        return rows;
    }

    public void setRows(List<BookInfo> rows) {
        this.rows = rows;
    }

    public PageDataResult(Integer total, List<BookInfo> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageDataResult() {
    }
}
