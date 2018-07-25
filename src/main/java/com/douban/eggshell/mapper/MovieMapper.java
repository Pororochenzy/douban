package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.Movie;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过id查找电影
     */
    Movie findMovieById(int id);

    /**
     * 通过电影名和导演名找电影（查重）
     */
    Movie findMovieByNameDirector(Map map);

    /**
     * 更新评分数据
     *
     * @param map 包含id和新grade
     */
    int updateGrade(Map map);

    /**
     * 更新评论数
     *
     * @param map 包含id和新comment_num
     */
    int updateCommentNum(Map map);

}
