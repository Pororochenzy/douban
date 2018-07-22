package com.douban.eggshell.pojo;

import lombok.Data;

@Data
public class Film_review {
    //    影评id
    private Integer id;

    //影评标题
    private String title;

    //影评内容
    private String comment;

//影评点赞数，即有用数
    private int support;

    //    影评反对数，即无用数
    private int oppose;

    //    影评创建时间
    private String createtime;

    private int isdelete;

//    //有多少个人对你的影评发表评论(还是通过别的表来查吧)
//    private  int respond_num;

    //以下两个是外键
    private Movie movie;

    private UserInfo userInfo;

    private  Score score;

}
