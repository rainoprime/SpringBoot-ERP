package com.liujinshui.bus.vo;

import com.liujinshui.bus.entity.Customer;

/**
 * @Author: liujinshui
 * @Description:
 * @Date:22:24ζζδΊ
 */
public class CustomerVo extends Customer {

    private Integer page;
    private Integer limit;

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
