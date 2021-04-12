package com.liujinshui.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liujinshui.bus.entity.Leavebill;
import com.liujinshui.bus.vo.LeavebillVo;
import com.liujinshui.common.utils.DataGridViewResult;
import com.liujinshui.common.utils.JSONResult;
import com.liujinshui.common.utils.SystemConstant;
import com.liujinshui.sys.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/bus/leavebill")
public class LeavebillController {

    @Resource
    private com.liujinshui.bus.service.LeavebillService LeavebillService;

    @RequestMapping("/leavebillList")
    public DataGridViewResult leavebillList(LeavebillVo leavebillVo, HttpSession session){
        //获取当前用户
        User user = (User) session.getAttribute(SystemConstant.LOGINUSER);
        //请假人
        leavebillVo.setUserid(user.getId());


        //创建分页查询
        IPage<Leavebill> page = new Page<>(leavebillVo.getPage(),leavebillVo.getLimit());

        //调用分页
        try {
            LeavebillService.findLeaveBillByPage(page,leavebillVo);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }
    @RequestMapping("/addLeavebill")
    public JSONResult addLeavebill(Leavebill leavebill, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(SystemConstant.LOGINUSER);
        leavebill.setUserid(user.getId());
        leavebill.setCreatetime(new Date());

        //设置审批人
        leavebill.setCheckPerson(user.getMgr());
        if(leavebill.getState()==SystemConstant.LEAVE_CREATE_STATE){
            leavebill.setState(SystemConstant.LEAVE_CREATE_STATE);
        }else if(leavebill.getState()==SystemConstant.LEAVE_CHECKING_STATE) {
            leavebill.setState(SystemConstant.LEAVE_CHECKING_STATE);
            leavebill.setCommittime(new Date());
        }
        leavebill.setCheckPerson(user.getMgr());

        if(LeavebillService.save(leavebill)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;


    }


    @RequestMapping("/updateLeavebill")
    public JSONResult updateLeavebill(Leavebill leavebill){
        if(leavebill.getState()==SystemConstant.LEAVE_CREATE_STATE){
            leavebill.setState(SystemConstant.LEAVE_CREATE_STATE);
        }else if(leavebill.getState()==SystemConstant.LEAVE_CHECKING_STATE) {
            leavebill.setState(SystemConstant.LEAVE_CHECKING_STATE);
            leavebill.setCommittime(new Date());
        }
        if(LeavebillService.updateById(leavebill)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

}

