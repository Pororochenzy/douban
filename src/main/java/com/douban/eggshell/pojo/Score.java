package com.douban.eggshell.pojo;

import lombok.Data;

@Data
public class Score {
    private Integer id;
    private int star;
    private String createtime;
    private int isdelete;
    private int user_info_id;
    private int movie_id;
}
