package com.douban.eggshell.enums;

public enum MovieEnums implements BaseEnums {
    UNKNOW_ERROR(0, "未知错误"),
    NO_FIND_RESULT(2, "无结果"),

    RANKING_GET_SUCCESS(1, "获取成功"),
    RANKING_GET_ERROR(-1, "获取失败"),

    MOVIE_GET_SUCCESS(1, "获取成功"),
    MOVIE_NOT_EXIST(-1, "电影不存在"),

    MOVIE_POST_SUCCESS(1, "添加成功"),
    MOVIE_POST_ERROR(-1, "添加失败"),
    MOVIE_IS_EXIST(-2, "电影已存在"),

    STYLE_NOT_EXIST(-2, "类型不存在");


    Integer code;
    String msg;

    MovieEnums(int i, String msg) {
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
