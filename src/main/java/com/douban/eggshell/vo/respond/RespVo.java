package com.douban.eggshell.vo.respond;

import lombok.Data;

@Data
public class RespVo {
    private Integer id ;
    private  String comment ;
    private String createtime;
    private RespUserVo userInfo ;

}
