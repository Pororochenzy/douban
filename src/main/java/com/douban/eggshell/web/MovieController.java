package com.douban.eggshell.web;

import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.MovieEnums;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.vo.MovieRankingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public Result rankingDefault() {
        //固定显示10条排行榜数据
        List<MovieRankingVO> movies = movieService.rankingDefault(10);
        if (movies != null) {
            return Result.build(MovieEnums.RANKING_GET_SUCCESS, movies);
        }
        return Result.build(MovieEnums.RANKING_GET_ERROR);
    }

}
