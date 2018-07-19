package com.douban.eggshell.web;

import com.douban.eggshell.pojo.User;
import com.douban.eggshell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*1.如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
 配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。*/

/*2.如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。*/

/*3.如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。*/

@RestController
public class HelloController {
@Autowired
    UserService userService;
    @RequestMapping("/hello")
    public  String hello(){
        List<User> users = userService.listAll();
        System.out.println(users);
        return "hi ,fuck eggshell";
    }
}
