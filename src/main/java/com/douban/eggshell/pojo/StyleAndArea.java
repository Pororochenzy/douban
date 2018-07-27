package com.douban.eggshell.pojo;

import lombok.Data;

/**
 * 这个类是多余的，当时为了测试那个模糊查询 分页 查不出 2次 的问题 而新建的
 */
@Data
public class StyleAndArea {
    private  String style;
    private String   area;
    private  int start;
    private  int end;
    private String range;
    private  String sort;
}
