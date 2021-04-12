package com.liujinshui.sys.vo;

import com.liujinshui.sys.entity.Dept;
import com.liujinshui.sys.entity.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DeptVo extends Dept {
    private Integer page;//当前页面
    private Integer limit;//每页显示的数据


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

}
