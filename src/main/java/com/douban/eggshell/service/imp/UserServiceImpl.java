package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.UserMapper;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.service.UserService;
import com.douban.eggshell.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public boolean addUser(User user) {

        boolean flag = false;

        String createtime = DataUtil.dataToString(new Date());

        user.setCreatetime(createtime);

        try {
            userMapper.addUser(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return flag;
    }


    @Override
    public boolean deleteUser(int id) {
        boolean flag = false;
        try {
            userMapper.deleteUser(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public User findByEmailPwd(String email, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        User user = userMapper.findByEmailPwd(map);
        if (user!=null) {
            return user;
        }

        return null;
    }

    @Override
    public User findUserById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public boolean checkByEmail(String email) {
        User user = userMapper.findByEmail(email);
        if(user!=null){
            return  true;
        }
        return  false;
    }

    @Override
    public boolean login(User user) {
        User u = userMapper.login(user);

        if(u!=null){
            return true;
        }
        return  false;
    }
}
