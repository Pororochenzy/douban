package com.douban.eggshell.vo;

import com.douban.eggshell.pojo.TypeArea;
import com.douban.eggshell.pojo.TypeStyle;
import lombok.Data;

import java.util.List;

@Data
public class TypeVo {
    List<TypeStyle> style_list;

    List<TypeArea> area_list;

    public TypeVo(List<TypeStyle> style, List<TypeArea> area) {
        this.style_list = style;
        this.area_list = area;
    }
}
