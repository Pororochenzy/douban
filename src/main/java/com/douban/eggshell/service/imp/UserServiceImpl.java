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
    public boolean addUser(User user) {
        boolean flag=false;
        try{
            userMapper.addUser(user);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag=false;
        try{
            userMapper.updateUser(user);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean flag=false;
        try{
            userMapper.deleteUser(id);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public User findUserByEmail(String Email) {
        return userMapper.findByEmail(Email);
    }

    @Override
    public User findUserById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public List<User> findAllUser() {
        return  userMapper.findAllUser();
    }
}
