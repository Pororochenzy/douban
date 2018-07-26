package com.douban.eggshell.web;

import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.pojo.Film_review;
import com.douban.eggshell.pojo.Score;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.service.ReviewService;
import com.douban.eggshell.service.UserInfoService;
import com.douban.eggshell.util.PageVoUtil;
import com.douban.eggshell.util.SessionUtil;
import com.douban.eggshell.vo.ReviewPageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j

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
    public Result review_get(@PathVariable("film_review_id") int reciew_id) {
//        log.info("传入的值是:{}",reciew_id);

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
    @RequestMapping(method = RequestMethod.POST)  //注意！！！ title 和comment 变量已经交换  comment  movie_id
    public Result review_post(@RequestParam(value = "movie_id") int movie_id,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "comment") String comment,
                              HttpServletRequest request) {

        log.info("movie_id是:" + movie_id);
        log.info("title是:" + title);

        if (!SessionUtil.isLogin(request)) {
            return Result.build(UserEnums.USER_NOT_LOGIN);
        }
        User currenuser = SessionUtil.getUser(request);
        log.info("session里的id,email,pwd是:{}", currenuser);
        //userinfo ID 正常来说是14 才对  但是出现了17 。
        //通过user表的id  查找 user_info表
        UserInfo userInfo = userInfoService.findByUser(currenuser);
        log.info("打印userinfo值：{}", userInfo);
        int ui_id = userInfo.getId();

        boolean flag = reviewService.addReview(title, comment, ui_id, movie_id);
        if (flag) {
            return Result.build(UserEnums.REVIEW_ADD_SUCCESS);
        }
        return Result.build(UserEnums.REVIEW_ADD_ERROR);

//        User currenuser = SessionUtil.getUser(request);
//        log.info("userinfo的id是:", currenuser.getId());
//        if (currenuser != null) {
//            int userinfo_id = userInfoService.findByUser(currenuser).getId();
//
//
//            boolean flag = reviewService.addReview(title, comment, userinfo_id, movie_id);
//            if (flag) {
//                return Result.build(UserEnums.REVIEW_ADD_SUCCESS);
//            } else {
//                return Result.build(UserEnums.REVIEW_ADD_ERROR);
//            }
//        } else {
//            return Result.build(UserEnums.USER_NOT_LOGIN);
//        }

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
        if (!SessionUtil.isLogin(request)) {
            return Result.build(UserEnums.USER_NOT_LOGIN);
        }
        User user = SessionUtil.getUser(request);
        UserInfo userInfo = userInfoService.findByUser(user);
//          log.info("打分模块，userInfo的值：{}", userInfo);
        Score scoreObj = reviewService.checkUserGiveScoreToOneMovie(userInfo.getId(), movie_id);
        if (scoreObj != null) {
            //拿到它打的几分
            int star = scoreObj.getStar();
            return Result.build(UserEnums.USER_ALEADLY_SCORE, scoreObj);
        }
        return Result.build(UserEnums.USER_NO_SCORE);


    }

    /**
     * 给影评点赞
     *
     * @param film_review_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/support", method = RequestMethod.PUT)
    public Result support(@RequestParam(value = "film_review_id") int film_review_id, HttpServletRequest request) {
        log.info("传入的值参数是：{}", film_review_id);
        //取消 登录才能点赞, SessionUtil.isLogin（）用户登录了后返回true,未登录返回false

//        if (SessionUtil.isLogin(request) == false) {
//            return Result.build((UserEnums.USER_NOT_LOGIN));
//        }

        //log.info("赞同，登录状态bollean值:,{}",SessionUtil.isLogin(request)); // 登录了 返回false

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
     *
     * @param film_review_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/oppose", method = RequestMethod.PUT)
    public Result oppose(@RequestParam(value = "film_review_id") int film_review_id, HttpServletRequest request) {
//
//        if (!SessionUtil.isLogin(request)) {
//            return Result.build((UserEnums.USER_NOT_LOGIN));
//        }


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

    /**
     * 最受欢迎的影评
     *
     * @param start
     * @param size
     * @return
     */
    @RequestMapping(value = "/best", method = RequestMethod.GET)
    public Result review_best(@RequestParam(value = "page", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        //2. 根据start,size进行分页，并且可以设置id 倒排序PageHelper.startPage(start,size,"id asc");
        PageHelper.startPage(start, size);

        //3. 因为PageHelper的作用，这里就会返回当前分页的集合了
        //因为这个 PageHelper工具类会帮我们在XML的id=“XXXX”的那SQl里的 自动为我们拼接limit() 做那些工作
        List<Film_review> bestReviews = reviewService.listReviewByWelcomeOrTime("welcome");

        ReviewPageVO pageVO = PageVoUtil.getVoByResults(bestReviews);

        if (bestReviews != null) {
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, pageVO);
        }
        return Result.build(UserEnums.REVIEWLIST_GET_ERROR);
        //4. 根据返回的集合List，创建PageInfo对象  (还没 封装之前的代码如下)
       /* PageInfo<Film_review> film_reviewPageInfo = new PageInfo<>(bestReviews);
        int cur_page = film_reviewPageInfo.getPageNum();//当前页数
        int total_page = film_reviewPageInfo.getPages(); //总页数
        long sum = film_reviewPageInfo.getTotal();//总影评数量
        if (bestReviews != null) {
            //包装进VO类 ，返回给前台
            ReviewPageVO br = new ReviewPageVO<>(); //它会自动帮你判定传入的泛型
            br.setCur_page(cur_page);
            br.setSum(sum);
            br.setTotal_page(total_page);
            br.setList(bestReviews);
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, br);
        }
        return Result.build(UserEnums.REVIEWLIST_GET_ERROR);*/

    }

    /**
     * 最新发表的影评
     *
     * @param start
     * @param size
     * @return
     */
    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public Result review_latest(@RequestParam(value = "page", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        //2. 根据start,size进行分页，并且可以设置id 倒排序PageHelper.startPage(start,size,"id asc");

        PageHelper.startPage(start, size);

        List<Film_review> lateReviews = reviewService.listReviewByWelcomeOrTime("latest");
        //log.info("lateReviews的值是{}", lateReviews);

        ReviewPageVO pageVO = PageVoUtil.getVoByResults(lateReviews);

        if (lateReviews != null) {
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, pageVO);
        }
        return Result.build(UserEnums.REVIEWLIST_GET_ERROR);
    }
}
