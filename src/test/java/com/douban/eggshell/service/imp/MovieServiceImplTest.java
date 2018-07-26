package com.douban.eggshell.service.imp;

import com.douban.eggshell.EggshellApplication;
import com.douban.eggshell.mapper.MovieMapper;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.util.DateUtil;
import com.douban.eggshell.vo.MovieRankingVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EggshellApplication.class)
public class MovieServiceImplTest {
    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieService movieService;

    @Test
    public void rankingDefault() {
        Map<String,Object> map = movieMapper.getScoreByMovieId(1);
        System.out.println( (int)(long)map.get("comment_num"));

    }

    @Test
    public void findMovieById() {
        System.out.println(movieService.findMovieById(1));
    }

    @Test
    public void isExistMovie() {
        System.out.println(movieService.isExistMovie("杜甫传", "李白"));
    }

    @Test
    public void updateGrade() {
        System.out.println(movieService.updateCommentNum(1, 50));
    }

    @Test
    public void rankingMovie() {
        List<MovieRankingVO> list = movieService.rankingMovie(null);
        list.forEach(System.out::println);
    }

    @Test
    public void searchMovie() {
        List<MovieRankingVO> list = movieService.searchMovie("杀手");
        list.forEach(System.out::println);

    }

    @Test
    public void addMovieScore() {
        Score score = new Score();
        score.setStar(1);
        score.setCreatetime(DateUtil.dataToString(new Date()));
        score.setMovie_id(5);
        score.setUser_info_id(9);
        System.out.println(movieService.addMovieScore(score));
    }
}