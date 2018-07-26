package com.douban.eggshell.service.imp;

import com.douban.eggshell.EggshellApplication;
import com.douban.eggshell.mapper.MovieMapper;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.vo.MovieRankingVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
        System.out.println(movieMapper.getScoreByMovieId(1));
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
}