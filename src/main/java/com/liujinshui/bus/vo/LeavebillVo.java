package com.liujinshui.bus.vo;

import com.liujinshui.bus.entity.Leavebill;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: liujinshui
 * @Description:
 * @Date:15:24星期三
 */
public class LeavebillVo extends Leavebill {
    private Integer page;//当前页码
    private Integer limit;//每页显示数量

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;//开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;//结束时间

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


}
