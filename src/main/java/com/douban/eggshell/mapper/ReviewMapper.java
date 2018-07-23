package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.Film_review;
import com.douban.eggshell.pojo.Score;

import java.util.List;
import java.util.Map;

public interface ReviewMapper {
    Film_review findReviewById(int review_id);

    int addReview(Map map);

    int addMovieScore(Map map);

    Score findMovieScoreByUserAndMovie_id(Map map);

    int addSupport(int film_review_id);

    int addOppose(int film_review_id);

    int findSupNum(int film_review_id);

    int findOppNum(int film_review_id);

    Film_review findReviewByid(int film_review_id);
    List<Film_review> listReview(Map map) ;
}
