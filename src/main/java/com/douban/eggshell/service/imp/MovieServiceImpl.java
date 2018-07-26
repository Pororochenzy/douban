package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.MovieMapper;
import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.vo.MovieRankingVO;
import com.douban.eggshell.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;


    @Override
    public List<MovieRankingVO> rankingMovie(String style) {
        if (style == null || style.trim().isEmpty()) {
            style = null;
        } else {
            style = "%" + style.trim() + "%";
        }
        Map<String, String> map = new HashMap<>();
        map.put("style", style);
        List<Movie> rankList = movieMapper.rankingMovie(map);
        return pojoListToVOList(rankList);
    }

    /**
     * 将电影的pojo列表转换为vo列表
     */
    private List<MovieRankingVO> pojoListToVOList(List<Movie> list) {
        if (list != null) {
            List<MovieRankingVO> voList = new ArrayList<>();
            for (Movie m : list) {
                voList.add(new MovieRankingVO(m));
            }
            return voList;
        }
        return null;
    }

    @Override
    public MovieVO findMovieById(int id) {
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
            return movieMapper.findMovieByNameDirector(map).size() > 0;
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

    @Override
    public boolean isExistStyle(String name) {
        if (name != null) {
            return movieMapper.findStyleByName(name) != null;
        }
        return false;
    }

    /**
     * 通过电影名搜索电影，模糊查询
     */
    @Override
    public List<MovieRankingVO> searchMovie(String name) {
        if (name != null && !name.trim().isEmpty()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "%" + name.trim() + "%");
            List<Movie> list = movieMapper.findMovieByNameDirector(map);
            return pojoListToVOList(list);
        }
        return null;
    }

    @Override
    public boolean addMovieScore(Score score) {
        //进来之前应先判断是否已经评过分
        if (score != null) {
            if (movieMapper.addMovieScore(score) > 0) {
                //评分增加成功，更新Movie表数据
                Map<String, Object> scoreMap = movieMapper.getScoreByMovieId(score.getMovie_id());
                if (scoreMap != null) {
                    double grade = ((BigDecimal) scoreMap.get("grade")).doubleValue();
                    int comment_num = (int) (long) scoreMap.get("comment_num");
                    grade = (double) Math.round(grade * 10) / 10;
                    return updateGrade(score.getMovie_id(), grade) &&
                            updateCommentNum(score.getMovie_id(), comment_num);
                }
            }
        }
        return false;
    }
}
