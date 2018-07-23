package com.douban.eggshell.vo;


import com.douban.eggshell.pojo.Film_review;
import lombok.Data;

import java.util.List;

/**
 * 该类用于包装 最受欢迎的影评排行
 */
@Data
public class BestReviewVO {

    private int cur_page;//当前页数
    private int total_page;//总页数
    private int sum;//总影评数量
    private List<Film_review> reviews;
}
