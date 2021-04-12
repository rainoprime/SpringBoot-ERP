package com.liujinshui.sys.vo;

import com.liujinshui.sys.entity.User;

import java.util.Set;

/**
 * 登录用户类
 *
 */
public class LoginUserVo {
    private User user;
    private Set<String> roles;
    private Set<String> permissions;


    public LoginUserVo() {
    }

    public LoginUserVo(User user, Set<String> roles, Set<String> permissions) {
        this.user = user;
        this.roles = roles;
        this.permissions = permissions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
