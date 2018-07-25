package com.douban.eggshell.dto;

import com.douban.eggshell.vo.MovieRankingVO;
import lombok.Data;

import java.util.List;

/**
 * 用于包装电影排行榜数据后，返回给前端，统一格式
 */
@Data
public class MovieRankingDTO {
    private List<MovieRankingVO> list;

}
