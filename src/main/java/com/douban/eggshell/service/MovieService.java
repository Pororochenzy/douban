package com.douban.eggshell.service;

import com.douban.eggshell.vo.MovieRankingVO;

import java.util.List;

public interface MovieService {

    List<MovieRankingVO> rankingDefault(int row_num);


}
