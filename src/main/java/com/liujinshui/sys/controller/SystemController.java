package com.liujinshui.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sys")
public class SystemController {

    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(){
        return "system/home/index";
    }

    @RequestMapping("/toDesktopManager")
    public String toDesktopManager(){ return "system/home/desktopManager";}

    /**
     * 日志管理页面
     * @return
     */
    @RequestMapping("/toLogManager")
    public String toLogManager(){ return "system/log/logManager";}

    /**
     * 公告管理页面
     * @return
     */
    @RequestMapping("/toNoticeManager")
    public String toNoticeManager(){ return "system/notice/noticeManager";}


    /**
     * 去到部门管理left树界面
     * @return
     */
    @RequestMapping("/toDeptLeft")
    public String toDeptLeft(){
        return "system/dept/left";
    }

    /**
     * 去到部门管理right
     * @return
     */
    @RequestMapping("/toDeptRight")
    public String toDeptRight(){
        return "system/dept/right";
    }

    /**
     * 去到部门管理right
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager(){
        return "system/dept/deptManager";
    }

    /**
     * 跳转到菜单管理页面
     * @return
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }
    /**
     * 跳转到菜单管理页面-left
     * @return
     */
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/left";
    }
    /**
     * 跳转到菜单管理页面-right
     * @return
     */
    @RequestMapping("/toMenuRight")
    public String toMenuRight() {
        return "system/menu/right";
    }
    /**
     * 跳转到权限管理页面
     * @return
     */
    @RequestMapping("/toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }
    /**
     * 跳转到权限管理页面-left
     * @return
     */
    @RequestMapping("/toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/left";
    }
    /**
     * 跳转到权限管理页面-right
     * @return
     */
    @RequestMapping("/toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/right";
    }



    /**
     * 跳转到角色管理页面
     * @return
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("/toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }
    /**
     * 跳转到用户管理页面-left
     * @return
     */
    @RequestMapping("/toUserLeft")
    public String toUserLeft() {
        return "system/user/left";
    }
    /**
     * 跳转到用户管理页面-right
     * @return
     */
    @RequestMapping("/toUserRight")
    public String toUserRight() {
        return "system/user/right";
    }




}