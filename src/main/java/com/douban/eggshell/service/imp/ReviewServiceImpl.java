package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.ReviewMapper;
import com.douban.eggshell.pojo.Film_review;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.service.ReviewService;
import com.douban.eggshell.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public Film_review findReviewByid(int film_review_id) {
        return reviewMapper.findReviewById(film_review_id);
    }

    @Override
    public boolean addReview(String title, String comment, int userinfo_id, int movie_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", title);
        map.put("comment", comment);
        map.put("userinfo_id", userinfo_id);
        map.put("movie_id", movie_id);
        map.put("createtime", DateUtil.dataToString(new Date()));
        int i = reviewMapper.addReview(map);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Score checkUserGiveScoreToOneMovie(Integer userinfo_id, Integer movie_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ui_id",userinfo_id);
        map.put("m_id",movie_id);
       return  reviewMapper.findMovieScoreByUserAndMovie_id(map);

    }

    @Override
    public boolean supportByReview_id(int film_review_id) {
        int num = reviewMapper.addSupport(film_review_id);
        if(num>0){
            return true;
        }
        return  false;
    }

    @Override
    public boolean opposeByReview_id(int film_review_id) {
        int num =  reviewMapper.addOppose(film_review_id);
        if(num>0){
            return true;
        }
        return  false;
    }

    @Override
    public int FindSupportNum(int film_review_id) {
        return reviewMapper.findSupNum(film_review_id);
    }

    @Override
    public int FindOpposeNum(int film_review_id) {
        return reviewMapper.findOppNum(film_review_id);
    }

    @Override
    public boolean checkReviewByid(int film_review_id) {
        Film_review review = reviewMapper.findReviewById(film_review_id);
        if(review!=null){
            return  true;
        }
        return  false;
    }

    @Override
    public List<Film_review> listReviewByWelcomeOrTime(String type) {
        Map<String,Object> typemap = new HashMap<>();
        if(type.equals("welcome")){
            typemap.put("welcome",1);
        }
       return  reviewMapper.listReview(typemap);


    }
}
