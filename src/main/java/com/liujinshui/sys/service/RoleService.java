package com.liujinshui.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liujinshui.sys.entity.Role;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-07
 */
public interface RoleService extends IService<Role> {
    //见方法名
    boolean saveRolePermission(int rid, String ids) throws Exception;

    Set<Integer> finRolePermissionByRoleId(Integer roleId) throws Exception;
}
