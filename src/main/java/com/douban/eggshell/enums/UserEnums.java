package com.douban.eggshell.enums;

public enum UserEnums implements BaseEnums {
    UNKNOW_ERROR(0, "未知错误"),

    USER_LOGIN_SUCCESS(1, "登录成功"),
    USER_PASSWORD_ERROR(-1, "密码错误"),
    USER_NOT_EXIST(-2, "用户不存在"),


    EMAIL_REGISTER_SUCCESS(1, "注册成功"),
    EMAIL_IS_REGISTER(-1, "邮箱已注册"),
    EMAIL_FORMAT_ERROR(-2, "邮箱格式不符"),

    EMAIL_IS_USEFUL(1, "邮箱可以使用"),

    USERINFO_GET_SUCCESS(1, "获取成功"),
    USERINFO_NOT_EXIST(-1, "用户不存在"),

    USERINFO_PUT_SUCCESS(1, "修改成功"),
    USERINFO_PUT_ERROR(-1, "修改失败"),
    USER_NOT_LOGIN(-2, "未登录"),

    USER_LOGOUT_SUCCESS(1, "退出成功"),
    USER_LOGOUT_ERROR(-1, "退出失败");

    Integer code;
    String msg;

    UserEnums(int i, String msg) {
        this.code = i;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
