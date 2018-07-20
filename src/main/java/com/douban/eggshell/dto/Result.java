package com.douban.eggshell.dto;


import com.douban.eggshell.enums.BaseEnums;

public class Result<T> {
    private int code;
    private T data; //里面放的是dto
    private String msg;

    public static Result build(BaseEnums baseEnums) {
        Result result = new Result();
        result.setCode(baseEnums.getCode());
        result.setMsg(baseEnums.getMsg());
        return result;
    }

    public static <T> Result build(BaseEnums baseEnums, T data) {
        Result result = new Result<>();
        result.setData(data);
        result.setCode(baseEnums.getCode());
        result.setMsg(baseEnums.getMsg());
        return result;
    }

    public static <T> Result build(int code, T data, String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static Result build(String msg) {
        Result result = new Result<>();
        result.setMsg(msg);
        result.setCode(0);
        return result;
    }

    public static Result build(int code, String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


    private Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
