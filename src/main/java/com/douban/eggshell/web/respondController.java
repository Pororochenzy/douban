package com.douban.eggshell.web;


import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.pojo.User;
import com.douban.eggshell.pojo.UserInfo;
import com.douban.eggshell.service.RespondService;
import com.douban.eggshell.service.UserInfoService;
import com.douban.eggshell.util.PageVoUtil;
import com.douban.eggshell.util.SessionUtil;
import com.douban.eggshell.vo.ReviewPageVO;
import com.douban.eggshell.vo.respond.RespVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class respondController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    RespondService respondService;

    @RequestMapping(value = "/respond", method = RequestMethod.GET)
    public Result respond_get(@RequestParam(value = "film_review_id") int fr_id,
                              @RequestParam(value = "page", defaultValue = "0") int start,
                              @RequestParam(value = "size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size,"createtime desc");
        List<RespVo> respVos = respondService.findResByReviewId(fr_id);
        if (respVos != null) {
            if (respVos.size() == 0) {
                return Result.build(UserEnums.NO_FIND_RESULT);
            }
            ReviewPageVO pageVO = PageVoUtil.getVoByResults(respVos);
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, pageVO);

        }
        return Result.build(UserEnums.REVIEWLIST_GET_ERROR);
    }

    @RequestMapping(value = "/respond", method = RequestMethod.POST)
    public Result respond_post(@RequestParam(value = "film_review_id") int film_review_id,
                               @RequestParam(value = "comment") String comment,
                               HttpServletRequest request) {
        boolean access = SessionUtil.isLogin(request);
        if (!access) {
            return Result.build(UserEnums.USER_NOT_LOGIN);
        }
        User user = SessionUtil.getUser(request);

        UserInfo userInfo = userInfoService.findByUser(user);
        boolean flag = respondService.addResond(comment, userInfo.getId(), film_review_id);
        //提交回应后 及时更新回应的数目

        if (flag) {
            return Result.build(UserEnums.COMMENT_SUCCESS);
        }
        return Result.build(UserEnums.COMMENT_ERROR);
    }
}
