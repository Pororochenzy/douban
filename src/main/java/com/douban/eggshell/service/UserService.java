package com.douban.eggshell.service;

import com.douban.eggshell.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean addUser(User user);




    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 用户登录
     * @param Email
     */

    int findUserByEmail(String email,String password);

    /**
     * 根据用户ID查询用户信息
     * @param userId
     */
    User findUserById(int userId);

    /**
     * 查找所有用户
     */
    List<User> findAllUser();
}
