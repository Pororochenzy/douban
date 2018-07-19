package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.UserMapper;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl  implements UserService{
    @Autowired UserMapper userMapper;

    @Override
    public List<User> listAll() {


        List<User> users = userMapper.listAll();
        return users;
    }
}
