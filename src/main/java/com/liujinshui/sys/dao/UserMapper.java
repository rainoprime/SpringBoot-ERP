package com.liujinshui.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liujinshui.sys.entity.User;
import com.liujinshui.sys.vo.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuJinshui
 * @since 2021-04-05
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询用户列表
     * @param page 分页信息
     * @param user 查询条件对象
     * @return
     * @throws Exception
     */
    IPage<User> findUserListByPage(@Param("page") IPage<User> page, @Param("user") UserVo user) throws Exception;

    @Select("select rid FROM sys_role_user WHERE uid = #{uid}")
    Set<Integer> findUserRoleByUserId(Integer id) throws Exception;

    @Delete("delete from sys_role_user where uid = #{userid}")
    void deleteUserRoleByUserId(int userId) throws Exception;

    @Insert("INSERT into sys_role_user (rid,uid) VALUES(#{rid},#{uid})")
    void insertUserRole(@Param("uid") int userId, @Param("rid")String rid) throws Exception;
}
