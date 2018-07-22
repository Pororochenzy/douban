package com.douban.eggshell.web;

import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.pojo.Film_review;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.service.ReviewService;
import com.douban.eggshell.service.UserInfoService;
import com.douban.eggshell.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    UserInfoService userInfoService;

    /**
     * 通过影评的id获取到影评的相关信息
     *
     * @param reciew_id
     * @return
     */
    @RequestMapping(value = "/{film_review_id}", method = RequestMethod.GET)
    public Result review(@PathVariable("film_review_id") int reciew_id) {

        //还有一个respond_num 没写

        Film_review review = reviewService.findReviewByid(reciew_id);
        if (review != null) {
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, review);

        }
        return Result.build(UserEnums.REVIEW_NOT_EXIST);
    }

    /**
     * 用户写影评
     *
     * @param movie_id
     * @param title
     * @param comment
     * @param request
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result review(@RequestParam(value = "movie_id") int movie_id, @RequestParam(value = "title") String title, @RequestParam(value = "comment") String comment, HttpServletRequest request) {
        User currenuser = (User) request.getSession().getAttribute("currentuser");
        if (currenuser != null) {
            int userinfo_id = userInfoService.findByUser(currenuser).getId();


            boolean falg = reviewService.addReview(title, comment, userinfo_id, movie_id);
            if (falg) {
                return Result.build(UserEnums.REVIEW_ADD_SUCCESS);
            } else {
                return Result.build(UserEnums.REVIEW_ADD_ERROR);
            }
        } else {
            return Result.build(UserEnums.USER_NOT_LOGIN);
        }

    }

    /**
     * 检查用户是否已经给当前电影打过分
     *
     * @param movie_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/check_is_score")
    public Result check_is_score(@RequestParam(value = "movie_id") int movie_id, HttpServletRequest request) {
        User currenuser = (User) request.getSession().getAttribute("currentuser");
        if (currenuser != null) {
            //判断一下用户是否有对当前这部电影有打过分
            int userinfo_id = userInfoService.findByUser(currenuser).getId();

            Score scoreObj = reviewService.checkUserGiveScoreToOneMovie(userinfo_id, movie_id);
            if (scoreObj != null) {//当前用户给该电影打过分了
                //拿到它打的几分
//                int star = scoreObj.getStar();
                return Result.build(UserEnums.USER_ALEADLY_SCORE, scoreObj);
            } else { //当前用户还未打分
                return Result.build(UserEnums.USER_NO_SCORE);
            }

        } else {
            return Result.build(UserEnums.USER_NOT_LOGIN);
        }

    }

    /**
     * 给影评点赞
     * @param film_review_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/support", method = RequestMethod.PUT)
    public Result support(@RequestParam(value = "film_review_id") int film_review_id, HttpServletRequest request) {

//         登录才能点赞
        if (!SessionUtil.isLogin(request)) {
            return Result.build((UserEnums.USER_NOT_LOGIN));
        }
//        检查传入的电影id是否存在
        if (!reviewService.checkReviewByid(film_review_id)) {
            return Result.build(UserEnums.REVIEW_NOT_EXIST);
        }
        boolean flag = reviewService.supportByReview_id(film_review_id);
        int SupportNum = reviewService.FindSupportNum(film_review_id);

        if (flag) {
            return Result.build(UserEnums.SUPPORT_SUCCESS, SupportNum);
        }
        return Result.build(UserEnums.SUP_OPP_ERROR);

    }

    /**
     * 给影评反对
     * @param film_review_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/oppose", method = RequestMethod.PUT)
    public Result oppose(@RequestParam(value = "film_review_id") int film_review_id, HttpServletRequest request) {
        if (!SessionUtil.isLogin(request)) {
            return Result.build((UserEnums.USER_NOT_LOGIN));
        }
//        检查传入的电影id是否存在
        if (!reviewService.checkReviewByid(film_review_id)) {
            return Result.build(UserEnums.REVIEW_NOT_EXIST);
        }
        boolean flag = reviewService.opposeByReview_id(film_review_id);
        int OpposeNum = reviewService.FindOpposeNum(film_review_id);
        if (flag) {
            return Result.build(UserEnums.OPPOSE_SUCCESS, OpposeNum);
        }
        return Result.build(UserEnums.SUP_OPP_ERROR);
    }

//    @RequestMapping(value = "/best",method =RequestMethod.GET)
//    public  Result review_best(@RequestParam(value = "page") int page){
//
//    }
}
