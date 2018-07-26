package com.douban.eggshell.web;

import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.BaseEnums;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.exception.IdentityException;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.service.UserInfoService;
import com.douban.eggshell.service.UserService;
import com.douban.eggshell.vo.UserInfoUpdateVo;
import com.douban.eggshell.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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

//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(XXX.class); 可以用注解 @Slf4j
@Slf4j
@RestController
@RequestMapping(value = "/api/identity")
public class IdentityController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登录
     *
     * @param email
     * @param pwd
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String pwd, HttpServletRequest request) {
        //1.先检查账号是否存在，2检查账号密码
        //只要给我传一个User进来，我就能给你一个userInfo

        //先找id先
//        userService.findByEmailPwd()
        boolean access = userService.checkByEmail(email);
        if (access) {
            User user = userService.findByEmailPwd(email, pwd);
            boolean flag = userService.login(user);
            if (flag) {
                HttpSession session = request.getSession();

                session.setAttribute("currentuser", user);
                return Result.build(UserEnums.USER_LOGIN_SUCCESS, userInfoService.findByUser(user));
            } else {
                return Result.build(UserEnums.USER_PASSWORD_ERROR);
            }
        } else {
            return Result.build(UserEnums.USER_NOT_EXIST);
        }

    }

    /**
     * 用户注册
     *
     * @param user
     * @param request
     * @param nickname
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(User user, HttpServletRequest request, @RequestParam(value = "nickname") String nickname) {
        //验证一次该邮箱是否已经注册
//        boolean access  = userService.checkByEmail(user.getEmail());
//        if(!access){
//            return  Result.build(UserEnums.EMAIL_IS_REGISTER);
//        }
        boolean flag = userService.addUser(user);

        if (flag) {
            User u = userService.findByEmailPwd(user.getEmail(), user.getPassword()); //参userid是没值的,参数u有
            log.info("查询到的" + u.getId());

            //添加user表 并拿到用户详情表的ID
            int keyID = userInfoService.addUserInfo(u, nickname);

            UserInfo userInfo = userInfoService.findUserInfoById(keyID);
            HttpSession session = request.getSession();
            session.setAttribute("currentuser", u);
            log.info("找到到userInfo,放入session");

            return Result.build(UserEnums.EMAIL_REGISTER_SUCCESS, userInfo);
        } else {
            log.info("用户增加失败");

            return Result.build(UserEnums.UNKNOW_ERROR);

        }
    }

    /**
     * 检查邮箱
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/checkEmail")
    public Result checkEmail(@RequestParam(value = "email") String email) {
        boolean flag;
        //如果email格式不正确
        if (false) {
            return Result.build(UserEnums.EMAIL_FORMAT_ERROR);
        }

        flag = userService.checkByEmail(email);

        if (flag) {
            return Result.build(UserEnums.EMAIL_IS_USEFUL);
        } else {
            return Result.build(UserEnums.EMAIL_IS_REGISTER);
        }

    }

    /**
     * 获得用户的详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public Result people_get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentuser = (User) session.getAttribute("currentuser");
        if (currentuser != null) {

            UserInfo userInfo = userInfoService.findByUser(currentuser);
            UserInfoVO uiv = new UserInfoVO();
            uiv.setEmail(currentuser.getEmail());
            uiv.setCreatetime(userInfo.getCreatetime());
            uiv.setImgurl(userInfo.getImgurl());
            uiv.setIntroduction(userInfo.getIntroduction());
            uiv.setNickname(userInfo.getNickname());
            uiv.setSex(userInfo.getSex());
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, uiv);
        } else {
            return Result.build(UserEnums.USER_NOT_LOGIN);
        }

    }

    /**
     * 用户详情 修改
     *
     * @param updateVo
     * @param request
     * @param avater
     * @return 服务器 图片的存放绝对路径是@C: Users//chen//AppData//Local//Temp//tomcat-docbase.4918370418639826426.80//uploaded//1532445687781屏幕截图(3).png
     */
    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public Result people_post(UserInfoUpdateVo updateVo, HttpServletRequest request, @RequestParam(value = "avater", required = false) MultipartFile avater) {
        HttpSession session = request.getSession();
        User currentuser = (User) session.getAttribute("currentuser");
        if (currentuser != null) {
//            给vo类把user表的id增加进去
            updateVo.setId(currentuser.getId());
            //假如有头像字段不为空的话

            if (avater != null) {
                //根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
                String fileName = System.currentTimeMillis() + avater.getOriginalFilename();
                //通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
                String destFileName = request.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
                //第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录
                File destFile = new File(destFileName);
                if(!destFile.getParentFile().exists()){
                    destFile.getParentFile().mkdirs();
                }
                try {
                    avater.transferTo(destFile);
                    //将图片在服务器的保存位置 赋给vo , 之后存入数据库

                    //返回一个相对路径的头像地址给前端
                    int index = destFileName.indexOf("uploaded");
                    String relPathName = destFileName.substring(index - 1);
                    updateVo.setImgurl(relPathName);

                    log.info("服务器 图片的存放绝对路径是{}", destFile);
//                    log.info("图片的存放路径是{}",destFileName);
//                    log.info("对象imgurl属性{}",updateVo.getImgurl());
//                    log.info("对象介绍属性{}",updateVo.getIntroduction());
//                    log.info("对象id属性{}",updateVo.getId());
//                    log.info("对象昵称属性{}",updateVo.getNickname());
//                    log.info("对象密码属性{}",updateVo.getPassword());
//                    log.info("对象性别属性{}",updateVo.getSex());


                } catch (IOException e) {
                    return Result.build("修改头像失败");
                }

            }
            boolean flag = userInfoService.updateUserInfo(updateVo);
            if (flag) {
                //执行people_get，相当于重新查一次user_info
                UserInfoVO data = (UserInfoVO) people_get(request).getData();
                return Result.build(UserEnums.USERINFO_PUT_SUCCESS, data);
            } else {
                return Result.build(UserEnums.USERINFO_PUT_ERROR);
            }
        }


        return Result.build(UserEnums.USER_NOT_LOGIN);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("currentuser");
        return Result.build(UserEnums.USER_LOGOUT_SUCCESS);
    }
}


   /* String password = "123";
    String salt = new SecureRandomNumberGenerator().nextBytes().toString();

    int times = 2;
    String algorithmName = "md5";

    String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();

	        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password,salt,times,encodedPassword);
    //获取数据库中的密码

    User user = new DAO().getUser(userName);
    String passwordInDB = user.getPassword();
    String salt = user.getSalt();
    String passwordEncoded = new SimpleHash("md5",password,salt,2).toString();

        if(null==user || !passwordEncoded.equals(passwordInDB))
                throw new AuthenticationException();

*/