package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.UserInfo;

public interface UserInfoMapper {
    int addUserInfo(UserInfo userInfo);
    UserInfo findUserById(int id);
}
