package com.douban.eggshell.service;

import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.vo.MovieRankingVO;

import java.util.List;

public interface MovieService {

    List<MovieRankingVO> rankingDefault(int row_num);

    Movie findMovieById(int id);

    boolean addMovie(Movie movie);

    boolean isExistMovie(String name, String director);

    boolean updateGrade(int id, double grade);

    boolean updateCommentNum(int id, int comment_num);

}
