package com.douban.eggshell.service;

import com.douban.eggshell.pojo.Film_review;
import com.douban.eggshell.pojo.Score;

import java.util.List;


public interface ReviewService {
    Film_review findReviewByid(int film_review_id);

    boolean addReview(String title, String comment, int userinfo_id, int movie_id);

    Score checkUserGiveScoreToOneMovie(Integer userinfo_id, Integer movie_id);

    boolean supportByReview_id(int film_review_id);

    boolean opposeByReview_id(int film_review_id);

    int FindSupportNum(int film_review_id);

    int FindOpposeNum(int film_review_id);

    boolean  checkReviewByid(int film_review_id);

    List<Film_review> listReviewByWelcomeOrTime(String type);
}
