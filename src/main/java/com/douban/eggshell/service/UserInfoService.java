package com.douban.eggshell.service;

import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.vo.UserInfoUpdateVo;

/**
 * 用户详情表
 */
public interface UserInfoService {
    int addUserInfo(User user, String nickname);

    UserInfo findUserInfoById(int id);

    UserInfo findByUser(User user);

    boolean updateUserInfo(UserInfoUpdateVo updateVo);
}
