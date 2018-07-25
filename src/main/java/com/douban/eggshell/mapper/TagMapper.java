package com.douban.eggshell.mapper;

import com.douban.eggshell.pojo.Style;
import com.douban.eggshell.pojo.TypeArea;
import com.douban.eggshell.pojo.TypeStyle;
import com.douban.eggshell.vo.TagGetMovieVo;

import java.util.List;
import java.util.Map;

public interface TagMapper {
    List<TypeArea> listArea();

    List<TypeStyle> listStyle();

    List<TagGetMovieVo> findByType(Map map);

    Style findStyleByName(String name);
}
