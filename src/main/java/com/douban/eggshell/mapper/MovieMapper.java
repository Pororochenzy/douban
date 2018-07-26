package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.pojo.TypeStyle;
import com.douban.eggshell.vo.MovieVO;

import java.util.List;
import java.util.Map;

public interface MovieMapper {

    /**
     * 获取排行榜
     *
     * @param map 设置type值以获取分类排行榜
     * @return list
     */
    List<Movie> rankingMovie(Map map);

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
    MovieVO findMovieById(int id);

    /**
     * 通过电影名和导演名找电影（查重），或只通过电影名查找（搜索）
     */
    List<Movie> findMovieByNameDirector(Map map);

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

    /**
     * 通过类型名查找以判断是否存在某类型
     */
    TypeStyle findStyleByName(String name);

    /**
     * 给电影增加评分
     */
    int addMovieScore(Score score);

    /**
     * 通过电影id获取最新的评分和评分人数数据
     */
    Map<String, Object> getScoreByMovieId(int id);

}
