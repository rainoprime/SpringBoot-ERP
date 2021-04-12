package com.liujinshui.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liujinshui.sys.dao.UserMapper;
import com.liujinshui.sys.entity.User;
import com.liujinshui.sys.service.UserService;
import com.liujinshui.sys.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByName(String userName) throws Exception {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("loginname",userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> findUserListByPage(IPage<User> page, UserVo user) throws Exception {
        return userMapper.findUserListByPage(page,user);
    }


    @Override
    public Set<Integer> findUserRoleByUserId(Integer id) throws Exception {
        return userMapper.findUserRoleByUserId(id);
    }

    @Override
    public boolean saveUserRole(int userId, String roleIds) throws Exception{
        try {
            //先删除sys_role_user表的数据
            userMapper.deleteUserRoleByUserId(userId);
            //再添加sys_role_user表的数据
            String [] rids = roleIds.split(",");
            //循环添加
            for (int i = 0; i < rids.length; i++) {
                userMapper.insertUserRole(userId,rids[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
