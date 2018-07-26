package com.douban.eggshell.web;

import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.MovieEnums;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.service.ReviewService;
import com.douban.eggshell.service.UserInfoService;
import com.douban.eggshell.util.DateUtil;
import com.douban.eggshell.util.PageVoUtil;
import com.douban.eggshell.util.SessionUtil;
import com.douban.eggshell.vo.MovieRankingVO;
import com.douban.eggshell.vo.MovieVO;
import com.douban.eggshell.vo.ReviewPageVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserInfoService userInfoService;


    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public Result rankingMovie(@RequestParam(value = "style", required = false) String style,
                               @RequestParam(value = "page", defaultValue = "1") int start,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        //先判读style是否在数据库中
        if (style != null && !style.trim().isEmpty()) {
            if (!movieService.isExistStyle(style.trim())) {
                return Result.build(MovieEnums.STYLE_NOT_EXIST);
            }
        }
        PageHelper.startPage(start, size);
        List<MovieRankingVO> list = movieService.rankingMovie(style);
        ReviewPageVO pageVO = PageVoUtil.getVoByResults(list);
        if (pageVO != null) {
            return Result.build(MovieEnums.RANKING_GET_SUCCESS, pageVO);
        }
        return Result.build(MovieEnums.RANKING_GET_ERROR);
    }

    @RequestMapping(value = "/{movie_id}", method = RequestMethod.GET)
    public Result movie_get(@PathVariable("movie_id") int id) {
        MovieVO movie = movieService.findMovieById(id);
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Result searchMovie(@RequestParam(value = "name") String name,
                              @RequestParam(value = "page", defaultValue = "1") int start,
                              @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(start, size);
        List<MovieRankingVO> list = movieService.searchMovie(name);
        ReviewPageVO pageVO = PageVoUtil.getVoByResults(list);
        if (pageVO != null) {
            return Result.build(MovieEnums.RANKING_GET_SUCCESS, pageVO);
        }
        return Result.build(MovieEnums.RANKING_GET_ERROR);
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public Result scoreMovie(@RequestParam(value = "movie_id") int movie_id,
                             @RequestParam(value = "score") int star,
                             HttpServletRequest request) {
        if (!SessionUtil.isLogin(request)) {
            return Result.build((UserEnums.USER_NOT_LOGIN));
        }
        if (movie_id > 0 && star >= 1 && star <= 5) {
            if (movieService.findMovieById(movie_id) == null) {
                //电影不存在
                return Result.build(MovieEnums.MOVIE_NOT_EXIST);
            }
            User cur_user = SessionUtil.getUser(request);
            int user_info_id = userInfoService.findByUser(cur_user).getId();
            Score score = reviewService.checkUserGiveScoreToOneMovie(user_info_id, movie_id);
            if (score == null) {
                //当前用户未对指定电影评过分
                score = new Score();
                score.setUser_info_id(user_info_id);
                score.setMovie_id(movie_id);
                score.setStar(star);
                score.setCreatetime(DateUtil.dataToString(new Date()));
                if (movieService.addMovieScore(score)) {
                    score = reviewService.checkUserGiveScoreToOneMovie(user_info_id, movie_id);
                    return Result.build(MovieEnums.SCORE_POST_SUCCESS, score.getStar());
                }
            }
        }
        return Result.build(MovieEnums.SCORE_POST_ERROR);
    }

}
