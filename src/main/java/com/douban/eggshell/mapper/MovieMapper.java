package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.Movie;

import java.util.List;

public interface MovieMapper {

    /**
     * 获取默认排行榜（根据评分）
     *
     * @param row_num 行数，即获取前几条
     * @return list
     */
    List<Movie> rankingByGrade(int row_num);

    /**
     * 添加电影
     *
     * @param movie Movie对象
     * @return int
     */
    int addMovie(Movie movie);


}
