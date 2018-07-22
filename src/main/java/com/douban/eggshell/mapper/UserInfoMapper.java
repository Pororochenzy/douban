package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.vo.UserInfoUpdateVo;

public interface UserInfoMapper {
    int addUserInfo(UserInfo userInfo);

    UserInfo findUserById(int id);

    UserInfo findByUser(User user);

    int updateUserInfo(UserInfoUpdateVo updateVo);
}
