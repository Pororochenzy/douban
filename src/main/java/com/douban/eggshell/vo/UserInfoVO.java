package com.douban.eggshell.vo;

import lombok.Data;

@Data
public class UserInfoVO {
    private String email;
    private String nickname;
    private String introduction;
    private int sex;
    private String imgurl;
    private String createtime;
}
