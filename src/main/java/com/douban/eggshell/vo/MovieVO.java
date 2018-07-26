package com.douban.eggshell.vo;

import lombok.Data;

@Data
public class MovieVO {
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

    private ScoreVO score_detail;
}
