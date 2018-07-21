package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
     * 根据邮箱密码 查找到 id
     *
     */
    User findByEmailPwd(Map map);

    /**
     * 根据用户ID查询用户信息
     *
     */
    User findById( int userId);


    /**
     * 查找所有用户
     */
    List<User>  findAllUser();

    /**
     * 通过email 查找用户
     */
    User findByEmail(String email);

    /**
     * 登录
     */
    User login(User user);
}
