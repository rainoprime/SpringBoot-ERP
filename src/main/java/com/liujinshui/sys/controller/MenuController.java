package com.liujinshui.sys.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liujinshui.sys.entity.Permission;
import com.liujinshui.sys.entity.User;
import com.liujinshui.sys.service.PermissionService;
import com.liujinshui.sys.service.RoleService;
import com.liujinshui.sys.service.UserService;
import com.liujinshui.common.utils.*;
import com.liujinshui.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Resource
    private PermissionService permissionService;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    /**
     * @Description: 加载首页菜单树
     * @Param:
     * @Return:
     * @Author: liujinshui
     * @Date: 2020/8/16 10:49
     */

    //所有数据都放在TreeNode中
    @RequestMapping("/loadIndexLeftMenuTree")
    public DataGridViewResult loadIndexLeftMenuTree(HttpSession session) {
        //创建条件构造器
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        //只查询列为type   值为menu的数据
        queryWrapper.eq("type", SystemConstant.TYPE_MENU);

        //要先判断用户的权限是什么,从Session中获取用户
        User LoginUser = (User) session.getAttribute(SystemConstant.LOGINUSER);
        //创建集合保存权限菜单
        List<Permission> permissionList = new ArrayList<>();

        //如果用户类型为0表示当前用户为超级管理员
        if (LoginUser.getType() == SystemConstant.SUPERUSER) {
            //调用查询语句菜单列表
            permissionList = permissionService.list(queryWrapper);

        } else {
            //普通用户   需要按照角色和权限查询
            //暂时List一下
            //permissionList = permissionService.list(queryWrapper);
            try {
                //根据当前用户id查询该用户拥有的角色列表
                Set<Integer> currentUserRoleIds = userService.findUserRoleByUserId(LoginUser.getId());
                //保存菜单集合  需要去重就选择set
                Set<Integer> pids = new HashSet<>();
                //循环遍历  当前用户拥有的角色列表
                for (Integer roleId : currentUserRoleIds) {
                    //4.根据角色Id查询每个角色下拥有的权限菜单
                    Set<Integer> permissionIds = roleService.finRolePermissionByRoleId(roleId);
                    //将查询出来的权限id放入集合中
                    pids.addAll(permissionIds);
                }
                //有数据就模糊查询，没有就算
                if (pids.size() > 0) {
                    //拼接查询条件
                    queryWrapper.in("id", pids);
                    //fa?
                    permissionList = permissionService.list(queryWrapper);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //创建集合，保存树节点
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (Permission permission : permissionList) {
            //判断当前节点是否展开，是则为true，否则为false
            Boolean spread = SystemConstant.OPEN_TRUE == permission.getOpen() ? true : false;
            TreeNode treeNode = new TreeNode();
            treeNode.setId(permission.getId());//菜单节点id
            treeNode.setPid(permission.getPid());//父节点编号
            treeNode.setHref(permission.getHref());//菜单路径
            treeNode.setIcon(permission.getIcon());//菜单图标
            treeNode.setTitle(permission.getTitle());//菜单名称
            treeNode.setSpread(spread);//是否展开
            //将树节点对象添加到树节点集合
            treeNodes.add(treeNode);
        }

        //构建层级关系
        List<TreeNode> treeNodeList = TreeNodeBuilder.build(treeNodes,1);


        return new DataGridViewResult(treeNodeList);
    }




    @RequestMapping("/loadMenuTreeLeft")
    public DataGridViewResult loadMenuTreeLeft(){

        //创建条件构造器对象
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        //只查询菜单,不查权限
        queryWrapper.eq("type",SystemConstant.TYPE_MENU);
        //查询所有菜单
        List<Permission> menuList = permissionService.list(queryWrapper);
        //创建节点集合
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //循环遍历所有菜单
        for (Permission permission : menuList) {
            //如果状态为1，节点为展开状态
            Boolean spread = permission.getOpen()== SystemConstant.OPEN_TRUE ? true : false;
            //封装树节点
            TreeNode treeNode = new TreeNode();
            treeNode.setId(permission.getId());//菜单编号
            treeNode.setPid(permission.getPid());//父级菜单编号
            treeNode.setTitle(permission.getTitle());//菜单名称
            treeNode.setSpread(spread);//展开状态
            //将树节点对象添加到节点集合
            treeNodes.add(treeNode);
        }
        //将树节点集合返回
        return new DataGridViewResult(treeNodes);

    }


    /**
     * 查询菜单列表
     * @param permissionVo
     * @return
     */
    @RequestMapping("/menulist")
    public DataGridViewResult menulist(PermissionVo permissionVo){
        //创建分页对象
        IPage<Permission> page = new Page<Permission>(permissionVo.getPage(),permissionVo.getLimit());
        //创建条件构造器对象
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        //只查询菜单
        queryWrapper.eq("type",SystemConstant.TYPE_MENU);
        //菜单名称查询
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        //菜单编号
        queryWrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId())
                .or().eq(permissionVo.getId()!=null,"pid", permissionVo.getId());
        //排序
        queryWrapper.orderByAsc("id");
        //调用查询的方法
        permissionService.page(page,queryWrapper);
        //返回数据
        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 添加菜单
     * @param permission
     * @return
     */
    @RequestMapping("/addMenu")
    public JSONResult addMenu(Permission permission){
        //指定type为menu菜单类型
        permission.setType(SystemConstant.TYPE_MENU);
        if(permissionService.save(permission)){
            return SystemConstant.ADD_SUCCESS;
        }
        return SystemConstant.ADD_ERROR;
    }

    /**
     * 修改菜单
     * @param permission
     * @return
     */
    @RequestMapping("/updateMenu")
    public JSONResult updateMenu(Permission permission){
        if(permissionService.updateById(permission)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }


    /**
     * 检查当前菜单下是否存在子节点
     * @param id
     * @return
     */
    @RequestMapping("/checkMenuHasChildren")
    public String checkMenuHasChildren(int id){
        Map<String,Object> map = new HashMap<String,Object>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        //根据父节点查询
        queryWrapper.eq("pid",id);
        //统计菜单数量
        int count = permissionService.count(queryWrapper);
        if(count>0){
            //存在子节点
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"对不起，当前菜单下有子节点，无法删除！");
        }else{
            //不存在子节点
            map.put(SystemConstant.EXIST,false);
        }
        //将map集合转换成json
        return JSON.toJSONString(map);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        if(permissionService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }else{
            return SystemConstant.DELETE_ERROR;
        }
    }

}
