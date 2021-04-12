package com.liujinshui.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liujinshui.sys.entity.Notice;

import com.liujinshui.sys.entity.User;
import com.liujinshui.sys.service.NoticeService;
import com.liujinshui.common.utils.DataGridViewResult;
import com.liujinshui.common.utils.JSONResult;
import com.liujinshui.common.utils.SystemConstant;
import com.liujinshui.sys.vo.NoticeVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-06
 */
@RestController
@RequestMapping("/sys/notice")
public class NoticeController{
    @Resource
    private NoticeService noticeService;


    /**
     * 查询公告列表
     * @param noticeVo
     * @return
     */
    @RequestMapping("/noticelist")
    public DataGridViewResult findnoticeList(NoticeVo noticeVo){

        //创建分页信息，参数1：当前页码，参数2：每页显示的数量
        IPage<Notice> noticeIPage = new Page<>(noticeVo.getPage(), noticeVo.getLimit());
        //创建条件构造器
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<Notice>();
        //标题
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        //发布人
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());

        //开始时间
        queryWrapper.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());

        //结束时间
        queryWrapper.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        queryWrapper.orderByDesc("createtime");

        //调用查询日志列表的方法
        noticeService.page(noticeIPage, queryWrapper);


        return new DataGridViewResult(noticeIPage.getTotal(),noticeIPage.getRecords());
    }

    @PostMapping("/addNotice")
    public JSONResult addNotice(Notice notice, HttpSession httpSession){
        //获取当前登录用户
        User user = (User) httpSession.getAttribute(SystemConstant.LOGINUSER);
        notice.setCreatetime(new Date());
        notice.setOpername(user.getName());
        if(noticeService.save(notice)){
            return SystemConstant.ADD_SUCCESS;
        }
        noticeService.save(notice);
        return SystemConstant.ADD_ERROR;
    }

    @PostMapping("/updateNotice")
    public JSONResult updateNotice(Notice notice, HttpSession httpSession){
        notice.setModifytime(new Date());
        //判断是否成功
        if(noticeService.updateById(notice)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        noticeService.save(notice);
        return SystemConstant.UPDATE_ERROR;
    }

    @RequestMapping("/deleteNotice")
    public JSONResult deleteNotice(int id ){
        //判断是否成功
        if(noticeService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

    @RequestMapping("/delete")
    public JSONResult delete(String ids){
        //将字符串拆分成数组
        String [] idsStr=ids.split(",");
        if(noticeService.removeByIds(Arrays.asList(idsStr))){
            //删除成功
            return SystemConstant.DELETE_SUCCESS;
        }

        return SystemConstant.DELETE_ERROR;
    }

}

