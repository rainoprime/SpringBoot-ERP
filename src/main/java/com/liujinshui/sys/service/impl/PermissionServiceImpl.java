package com.liujinshui.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujinshui.sys.dao.PermissionMapper;
import com.liujinshui.sys.entity.Permission;
import com.liujinshui.sys.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-06
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public boolean removeById(Serializable id) {
        //根据菜单id或权限id删除sys_role_permission权限菜单关系表数据
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);
    }

    @Override
    public List<Integer> findRolePermissionByRoleId(int roleId) {
        return permissionMapper.findRolePermissionByRoleId(roleId);
    }
}
