package com.douban.eggshell.web;

import com.douban.eggshell.dto.Result;
import com.douban.eggshell.enums.UserEnums;
import com.douban.eggshell.mapper.TagMapper;
import com.douban.eggshell.service.TagService;
import com.douban.eggshell.util.PageVoUtil;
import com.douban.eggshell.vo.ReviewPageVO;
import com.douban.eggshell.vo.TagGetMovieVo;
import com.douban.eggshell.vo.TypeVo;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/api/tag")
public class TagController {

    @Autowired
    TagService tagService;
    @Autowired
    TagMapper tagMapper;

    @RequestMapping(value = "/label", method = RequestMethod.GET)
    public Result label() {
        TypeVo typeVo = new TypeVo(tagService.listStyle(), tagService.listArea());

        return Result.build(UserEnums.USERINFO_GET_SUCCESS, typeVo);
    }

    /**
     *  有bug
     *  不能够正常 分页，它把全部结果都打印出来了
     * @param sort
     * @param range
     * @param tags
     * @param start
     * @param size
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result getMovieByTag(@RequestParam(value = "sort", defaultValue = "H") String sort,
                                @RequestParam(value = "range", defaultValue = "0,10") String range,
                                @RequestParam(value = "tags", required = false) String tags,
                                @RequestParam(value = "page", defaultValue = "0") int start,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
//        log.info("sort的值是:{}",sort);
//        log.info("range:{}",range);
        if(tags.trim().equals("")){
            tags = null;
        }
        log.info("tags的值是:{}",tags);
        
        PageHelper.startPage(start, size);
        
        /*有bug*/

       /* Map<String,Object> map  = new HashMap<>();
        map.put("sore","T");
        map.put("start",0);
        map.put("end",10);
        map.put("area","美国");

        List<TagGetMovieVo> tagGetMovieVos = tagMapper.findByType(map);*/
        List<TagGetMovieVo> tagGetMovieVos = tagService.findByType(sort, range, tags);

        log.info("tagGetMovieVos的值是{}",tagGetMovieVos);

        if (tagGetMovieVos != null) {

            ReviewPageVO pageVO = PageVoUtil.getVoByResults(tagGetMovieVos);

//            log.info("pageVO的值是{}",pageVO);
//            log.info("pageVO的size是",pageVO.getTotal_page());

            if(pageVO.getSum()==0){
                return Result.build(UserEnums.NO_FIND_RESULT);
            }
            return Result.build(UserEnums.USERINFO_GET_SUCCESS, pageVO);

        }
        return Result.build(UserEnums.UNKNOW_ERROR);
    }
}
