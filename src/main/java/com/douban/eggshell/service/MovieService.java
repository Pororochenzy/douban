package com.douban.eggshell.service;

import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.vo.MovieRankingVO;
import com.douban.eggshell.vo.MovieVO;

import java.util.List;

public interface MovieService {

    List<MovieRankingVO> rankingMovie(String style);

    MovieVO findMovieById(int id);

    boolean addMovie(Movie movie);

    boolean isExistMovie(String name, String director);

    boolean updateGrade(int id, double grade);

    boolean updateCommentNum(int id, int comment_num);

    boolean isExistStyle(String name);

    List<MovieRankingVO> searchMovie(String name);

    boolean addMovieScore(Score score);

}
