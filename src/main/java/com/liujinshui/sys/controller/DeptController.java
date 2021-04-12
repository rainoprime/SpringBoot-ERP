package com.liujinshui.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liujinshui.sys.entity.Dept;
import com.liujinshui.sys.service.DeptService;
import com.liujinshui.common.utils.DataGridViewResult;
import com.liujinshui.common.utils.JSONResult;
import com.liujinshui.common.utils.SystemConstant;
import com.liujinshui.common.utils.TreeNode;
import com.liujinshui.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Resource
    private DeptService deptService;


    @RequestMapping("/loadDeptTreeLeft")
    public DataGridViewResult loadDeptTreeLeft(){
        //查询所有部门
        List<Dept> deptList=deptService.list();
        //创建树节点集合
        List<TreeNode>  treeNodes = new ArrayList<>();
        //遍历所有部门

        for (Dept dept : deptList) {
            Boolean spread = dept.getOpen()== SystemConstant.OPEN_TRUE ? true : false;
            //封装树节点

            TreeNode treeNode = new TreeNode();
            treeNode.setId(dept.getId());//部门编号
            treeNode.setPid(dept.getPid());//父部门编号
            treeNode.setTitle(dept.getTitle());//部门名称
            treeNode.setSpread(spread);//展开状态
            //将树节点添加到节点集合
            treeNodes.add(treeNode);
        }

        return new DataGridViewResult(treeNodes);

    }

    @RequestMapping("/deptlist")
    public DataGridViewResult deptlist(DeptVo deptVo){
        //创建分页对象
        IPage<Dept> page=new Page<>(deptVo.getPage(),deptVo.getLimit());
        //创建条件构造对象
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        //部门名称查询
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        //部门编号
        queryWrapper.eq(deptVo.getId()!=null,"id",deptVo.getId()).or().eq(deptVo.getId()!=null,"pid",deptVo.getId());

        //地址
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()),"address",deptVo.getAddress());
        //排序
        queryWrapper.orderByAsc("id");

        deptService.page(page,queryWrapper);

        return  new DataGridViewResult(page.getTotal(),page.getRecords());
    }
    @RequestMapping("/addDept")
    public JSONResult addDept(Dept dept){
        dept.setCreatetime(new Date());
        if (deptService.save(dept)){

            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }


    @RequestMapping("/updateDept")
    public JSONResult updateDept(Dept dept){
        if (deptService.updateById(dept)){

            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

    /**
     * 检查当前部门是否有子节点
     * @param id
     * @return
     */
    @RequestMapping("checkDeptHasChildren")
    public String checkDeptHasChildren(int id ){

        Map<String,Object> map =  new HashMap<String,Object>();
        QueryWrapper<Dept> queryWrapper =new QueryWrapper<>();
        //根据父节点查询
        queryWrapper.eq("pid",id);
        //统计部门数量
        int count = deptService.count(queryWrapper);
        if (count > 0 ){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"对不起，当前部门下有子节点，无法删除！");

        }else{
            //不存在子节点
            map.put(SystemConstant.EXIST,false);
        }

        return JSON.toJSONString(map);
    }

    @RequestMapping("deleteById")
    public JSONResult deleteById(int id ){
        if(deptService.removeById(id )){
            return SystemConstant.DELETE_SUCCESS;
        }else{
            return SystemConstant.DELETE_ERROR;
        }
    }
}

