package com.liujinshui.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujinshui.sys.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-06
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Delete("delete from sys_role_permission where pid =#{id}")
    void deleteRolePermissionByPid(Serializable id);


    @Select("select pid from sys_role_permission where rid = #{roleId}")
    List<Integer> findRolePermissionByRoleId(int roleId);
}
