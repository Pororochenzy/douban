package com.douban.eggshell.pojo;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;
    private String nickname;
    private String introduction;
    private Integer sex;
    private String imgurl;
    private String createtime;
    private User user;


}
