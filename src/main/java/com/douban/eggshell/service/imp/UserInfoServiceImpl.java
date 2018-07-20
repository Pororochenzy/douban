package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.UserInfoMapper;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int addUserInfo(User user,String nickname) {
        UserInfo ui = new UserInfo();
        ui.setUser(user);
        ui.setNickname(nickname);

       userInfoMapper.addUserInfo(ui);
       int keyID = ui.getId();
            if(keyID>0){
                return keyID;
            }
        return -1;
    }

    @Override
    public UserInfo findUserInfoById(int id) {
        UserInfo userInfo = userInfoMapper.findUserById(id);
        if(userInfo!=null){
            return userInfo;
        }
        return null;
    }
}
