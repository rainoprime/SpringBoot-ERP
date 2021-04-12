package com.liujinshui.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liujinshui.sys.entity.Log;
import com.liujinshui.sys.service.LogService;
import com.liujinshui.common.utils.DataGridViewResult;
import com.liujinshui.common.utils.JSONResult;
import com.liujinshui.common.utils.SystemConstant;
import com.liujinshui.sys.vo.LogVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/sys/log")
public class LogController {

    @Resource
    private LogService logService;

    @RequestMapping("/loglist")
    public DataGridViewResult findLogList(LogVo logVo){

        //创建分页信息，参数1：当前页码，参数2：每页显示的数量
        IPage<Log> logIPage = new Page<>(logVo.getPage(), logVo.getLimit());
        //创建条件构造器
        QueryWrapper<Log> queryWrapper = new QueryWrapper<Log>();


        //操作类型

        queryWrapper.eq(StringUtils.isNotBlank(logVo.getType()),"type",logVo.getType());


        //登录名称
        queryWrapper.like(StringUtils.isNotBlank(logVo.getLoginname()),"loginname",logVo.getLoginname());

        //开始时间
        queryWrapper.ge(logVo.getStartTime()!=null,"createtime",logVo.getStartTime());

        //结束时间
        queryWrapper.le(logVo.getEndTime()!=null,"createtime",logVo.getEndTime());

        queryWrapper.orderByDesc("createtime");
        //调用查询日志列表的方法
        logService.page(logIPage, queryWrapper);


        return new DataGridViewResult(logIPage.getTotal(),logIPage.getRecords());
    }

    @RequestMapping("/delete")
    public JSONResult delete(String ids){
        //将字符串拆分成数组
        String [] idsStr=ids.split(",");
        if(logService.removeByIds(Arrays.asList(idsStr))){
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }

        return SystemConstant.DELETE_ERROR;
    }
}

