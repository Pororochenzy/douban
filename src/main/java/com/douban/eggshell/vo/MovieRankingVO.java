package com.douban.eggshell.vo;

import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.util.StrUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieRankingVO {

    private Integer id;
    /**
     * 名字与别名的合并
     **/
    private String name;
    private String imgurl;
    /**
     * 导演、编剧、主演、类型、地区、语言、上映日、片长 的合并
     **/
    private String detail;
    private double grade;
    private int comment_num;

    //    private String createtime;
    //    private int isdelete;

    public MovieRankingVO() {
    }

    public MovieRankingVO(Movie movie) {
        this.id = movie.getId();
        this.name = StrUtil.strLimitLen(StrUtil.strMerge(movie.getName(),
                StrUtil.getPartDelimiter(movie.getAlias(), 2)), 32);
        this.imgurl = movie.getImgurl();
        List<String> attrList = new ArrayList<>();
        attrList.add(movie.getRelease_date());
        attrList.add(movie.getActor());
        attrList.add(movie.getArea());
        attrList.add(movie.getDirector());
        attrList.add(movie.getRunning_time());
        attrList.add(movie.getStyle());
        attrList.add(movie.getScriptwriter());
        attrList.add(movie.getLanguage());
        this.detail = StrUtil.strLimitLen(StrUtil.strMergeArray(attrList), 175);
        this.grade = movie.getGrade();
        this.comment_num = movie.getComment_num();
    }
}
