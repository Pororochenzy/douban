package com.douban.eggshell.service;

import com.douban.eggshell.pojo.TypeArea;
import com.douban.eggshell.pojo.TypeStyle;
import com.douban.eggshell.vo.TagGetMovieVo;

import java.util.List;

public interface TagService {
    List<TypeArea> listArea();

    List<TypeStyle> listStyle();

    List<TagGetMovieVo> findByType(String sort,String range,String tags);


}
