package com.douban.eggshell.service;

import com.douban.eggshell.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    boolean addUser(User user);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 根据邮箱密码得到User
     *
     *
     */

    User findByEmailPwd(String email, String password);

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId
     */
    User findUserById(int userId);

    /**
     * 查找所有用户
     */
    List<User> findAllUser();

    /**
     * 检查邮箱是否存在
     * @param email
     * @return
     */
    boolean checkByEmail(String email);

    boolean login(User user);


}
