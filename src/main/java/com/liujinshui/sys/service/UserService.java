package com.liujinshui.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liujinshui.sys.entity.User;
import com.liujinshui.sys.vo.UserVo;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-05
 */
public interface UserService extends IService<User> {

    User findUserByName(String userName) throws Exception;
    IPage<User> findUserListByPage(IPage<User> page, UserVo user) throws Exception;

    Set<Integer> findUserRoleByUserId(Integer id) throws Exception;

    boolean saveUserRole(int userId, String roleIds) throws Exception;
}
