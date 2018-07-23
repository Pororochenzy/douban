package com.douban.eggshell.util;

import com.douban.eggshell.pojo.User;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    public static User getUser(HttpServletRequest request) {
        User currentuser = (User) request.getSession().getAttribute("currentuser");
        return currentuser;
    }

    public static boolean isLogin(HttpServletRequest request) {
        User currentuser = (User) request.getSession().getAttribute("currentuser");
        if (currentuser != null) {
            return true;
        }
        return false;
    }

}
