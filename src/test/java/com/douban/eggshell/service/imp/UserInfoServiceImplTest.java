package com.douban.eggshell.service.imp;

import com.douban.eggshell.EggshellApplication;
import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.exception.IdentityException;
import com.douban.eggshell.mapper.UserInfoMapper;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EggshellApplication.class)
public class UserInfoServiceImplTest {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserService userService;
//    final static Logger logger  =  LoggerFactory.getLogger(Test.class );

    @Test
    public void addUserInfo() {

        UserInfo user = userInfoMapper.findUserById(0);

        /* 不加注解@Slf4j的传统方式
        logger.debug( " This time is {}" ,  new Date().toString());
        logger.info( " This time is {}" , user);
        logger.warn( " This time is {}" ,  user);
        logger.error( " This time is {}" ,  user);*/

//        注解方式实现日志
//        默认是输出 info之上的 ， 由下图可知 debug，trace不输出
        log.warn("warn message is {}" , "hahaha ");
        log.info("info message");
        log.error("error message");
    }
    @Test
    public  void checkEmail() throws IdentityException {
        try{
            String email = "test";
            boolean flag = userService.checkByEmail(email);
            if (flag) {
                log.info("返回结果是:{}",Result.build(UserEnums.EMAIL_IS_USEFUL));
            } else {
                log.info("返回结果是:{}",UserEnums.EMAIL_IS_REGISTER);
            }
        }catch(Exception e ){
            throw  new IdentityException("未知错误");
        }

    }
}