package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.UserMapper;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.service.UserService;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String createtime = sdf.format(new Date());

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
    public int findUserByEmail(String email, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        int id = userMapper.findByEmail(map);
        if (id > 0) {
            return id;
        }

        return 0;
    }

    @Override
    public User findUserById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
