package com.douban.eggshell.exception;

public class IdentityException extends  Exception {
    //异常信息
    private String message;

    public IdentityException(String message){
        super(message);
        this.message = message;
    }



    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
