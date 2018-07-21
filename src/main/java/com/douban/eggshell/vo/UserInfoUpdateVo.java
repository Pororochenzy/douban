package com.douban.eggshell.vo;

import lombok.Data;


@Data
public class UserInfoUpdateVo {
    private Integer id;
    private String password;
    private String nickname;
    private String introduction;
    private int sex;
    private String imgurl;

}
