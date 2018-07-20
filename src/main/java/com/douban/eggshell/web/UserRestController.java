package com.douban.eggshell.web;

import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.service.UserInfoService;
import com.douban.eggshell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Title: UserRestController
 * Description:
 * 用户数据操作接口
 * Version:1.0.0
 */

/*1.如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
 配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。*/

/*2.如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。*/

/*3.如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。*/

@RestController
@RequestMapping(value = "/api/identity")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

//    @RequestMapping(value ="/login",method = RequestMethod.POST)
//    public User login(User user){
//        System.out.println("参数"+user);
//        boolean flag = userService.findUserByEmail(user.getEmail(), user.getPassword());
//        if(flag){
//
//        }
//        return user;
//    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserInfo register(User user, HttpServletRequest request, @RequestParam(value = "nickname") String nickname) {
        boolean flag = userService.addUser(user);

        if (flag) {
            int userID = userService.findUserByEmail(user.getEmail(), user.getPassword());
            user.setId(userID);
//拿到用户详情表的ID
            int keyID = userInfoService.addUserInfo(user, nickname);
            System.out.println("用户详情id值是：" + keyID);


            //准备返回查询回的用户信息
            System.out.println("用户表和用户信息表增加成功");

            return userInfoService.findUserInfoById(keyID);

        } else {
            System.out.println("用户增加失败");

        }
//        Session.setAttribute("currentuser",);

        return null;
    }
}

