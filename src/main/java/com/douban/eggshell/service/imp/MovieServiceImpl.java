package com.douban.eggshell.service.imp;

import com.douban.eggshell.mapper.MovieMapper;
import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.service.MovieService;
import com.douban.eggshell.vo.MovieRankingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<MovieRankingVO> rankingDefault(int row_num) {
        if (row_num <= 0) {
            row_num = 1;
        }
        List<Movie> rankList = movieMapper.rankingByGrade(row_num);
        if (rankList != null) {
            List<MovieRankingVO> dataList = new ArrayList<>();
            for (Movie m : rankList) {
                dataList.add(new MovieRankingVO(m));
            }
            return dataList;
        }
        return null;
    }
}
