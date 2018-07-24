package com.douban.eggshell.pojo;

import lombok.Data;

@Data
public class Movie {
    private Integer id;
    private String name;
    private String imgurl;
    private String director;
    private String scriptwriter;
    private String actor;
    private String style;
    private String area;
    private String language;
    private String release_date;
    private String running_time;
    private String alias;
    private String introduction;
    private double grade;
    private int comment_num;
    private String createtime;
    private int isdelete;

}