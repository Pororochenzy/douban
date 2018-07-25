package com.douban.eggshell.web;

import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.pojo.Film_review;
import com.douban.eggshell.service.TagService;
import com.douban.eggshell.util.PageVoUtil;
import com.douban.eggshell.vo.ReviewPageVO;
import com.douban.eggshell.vo.TagGetMovieVo;
import com.douban.eggshell.vo.TypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/api/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/label", method = RequestMethod.GET)
    public Result label() {
        TypeVo typeVo = new TypeVo(tagService.listStyle(), tagService.listArea());

        return Result.build(UserEnums.USERINFO_GET_SUCCESS, typeVo);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result getMovieByTag(@RequestParam(value = "sort", defaultValue = "H") String sort,
                                @RequestParam(value = "range", defaultValue = "0,10") String range,
                                @RequestParam(value = "tags", required = false) String tags,
                                @RequestParam(value = "page", defaultValue = "0") int start,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
//        log.info("sort的值是:{}",sort);
//        log.info("range:{}",range);
//        log.info("tags的值是:{}",tags);
        PageHelper.startPage(start, size);

        List<TagGetMovieVo> tagGetMovieVos = tagService.findByType(sort, range, tags);
        if (tagGetMovieVos != null) {
            ReviewPageVO pageVO = PageVoUtil.getVoByResults(tagGetMovieVos);
            if(pageVO.getSum()==0){
                return Result.build(UserEnums.NO_FIND_RESULT);
            }
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, pageVO);

        }
        return Result.build(UserEnums.UNKNOW_ERROR);
    }
}
