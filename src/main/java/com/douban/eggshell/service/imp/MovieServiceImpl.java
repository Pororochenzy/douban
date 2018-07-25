package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.MovieMapper;
import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.vo.MovieRankingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<MovieRankingVO> rankingDefault(int row_num) {
        if (row_num <= 0) {
            row_num = 1;
        }
        List<Movie> rankList = movieMapper.rankingByGrade(row_num);
        if (rankList != null) {
            List<MovieRankingVO> dataList = new ArrayList<>();
            for (Movie m : rankList) {
                dataList.add(new MovieRankingVO(m));
            }
            return dataList;
        }
        return null;
    }

    @Override
    public Movie findMovieById(int id) {
        if (id > 0) {
            return movieMapper.findMovieById(id);
        }
        return null;
    }

    @Override
    public boolean addMovie(Movie movie) {
        if (movie != null) {
            return movieMapper.addMovie(movie) > 0;
        }
        return false;
    }

    @Override
    public boolean isExistMovie(String name, String director) {
        if (name != null && director != null) {
            Map<String, String> map = new HashMap<>();
            map.put("name", name);
            map.put("director", director);
            return movieMapper.findMovieByNameDirector(map) != null;
        }
        return false;
    }

    @Override
    public boolean updateGrade(int id, double grade) {
        if (id > 0 && grade >= 0 && grade <= 10) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("grade", grade);
            return movieMapper.updateGrade(map) > 0;
        }
        return false;
    }

    @Override
    public boolean updateCommentNum(int id, int comment_num) {
        if (id > 0 && comment_num >= 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("id", id);
            map.put("comment_num", comment_num);
            return movieMapper.updateCommentNum(map) > 0;
        }
        return false;
    }
}
