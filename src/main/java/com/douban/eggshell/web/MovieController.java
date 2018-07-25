package com.douban.eggshell.web;

import com.douban.eggshell.dto.MovieRankingDTO;
import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.MovieEnums;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.util.SessionUtil;
import com.douban.eggshell.vo.MovieRankingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public Result rankingDefault(@RequestParam(value = "row_num", defaultValue = "10") int row_num) {
        //固定显示10条排行榜数据
        List<MovieRankingVO> movies = movieService.rankingDefault(row_num);
        if (movies != null) {
            MovieRankingDTO data = new MovieRankingDTO();
            data.setList(movies);
            return Result.build(MovieEnums.RANKING_GET_SUCCESS, data);
        }
        return Result.build(MovieEnums.RANKING_GET_ERROR);
    }

    @RequestMapping(value = "/{movie_id}", method = RequestMethod.GET)
    public Result movie_get(@PathVariable("movie_id") int id) {
        Movie movie = movieService.findMovieById(id);
        if (movie != null) {
            return Result.build(MovieEnums.MOVIE_GET_SUCCESS, movie);
        }
        return Result.build(MovieEnums.MOVIE_NOT_EXIST);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result movie_post(Movie movie, HttpServletRequest request) {
        //暂时未做权限控制，只判断登录状态
        if (!SessionUtil.isLogin(request)) {
            return Result.build((UserEnums.USER_LACK_PERMISSION));
        }
        //判断是否已经存在该电影（只通过电影名和导演名判断）
        if (movie != null && movieService.isExistMovie(movie.getName(), movie.getDirector())) {
            return Result.build(MovieEnums.MOVIE_IS_EXIST);
        }
        if (movieService.addMovie(movie)) {
            return Result.build(MovieEnums.MOVIE_POST_SUCCESS);
        }
        return Result.build(MovieEnums.MOVIE_POST_ERROR);
    }

}
