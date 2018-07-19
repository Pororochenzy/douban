package com.douban.eggshell.service;

import com.douban.eggshell.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean updateUser(User user);


    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 根据用户名字查询用户信息
     * @param Email
     */
    User findUserByEmail(String Email);

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
