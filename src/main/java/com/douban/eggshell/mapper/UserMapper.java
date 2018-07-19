package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface UserMapper {

    /**
     * 用户数据新增
     */
    void addUser(User user);

    /**
     * 用户数据修改
     */
    void updateUser(User user);

    /**
     * 用户数据删除
     */
    void deleteUser(int id);

    /**
     * 根据用户名称查询用户信息
     *
     */
    User findByEmail(String eeee);

    /**
     * 根据用户ID查询用户信息
     *
     */
    User findById( int userId);


    /**
     * 查找所有用户
     */
    List<User>  findAllUser();



}
