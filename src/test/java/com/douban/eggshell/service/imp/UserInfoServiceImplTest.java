package com.douban.eggshell.service.imp;

import com.douban.eggshell.EggshellApplication;
import com.douban.eggshell.mapper.UserInfoMapper;
import com.douban.eggshell.pojo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EggshellApplication.class)
public class UserInfoServiceImplTest {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void addUserInfo() {

        UserInfo user = userInfoMapper.findUserById(0);
        System.out.println(user);
    }
}