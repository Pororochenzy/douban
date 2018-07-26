package com.douban.eggshell.mapper;

import com.douban.eggshell.EggshellApplication;
import com.douban.eggshell.pojo.Movie;
import com.douban.eggshell.util.DateUtil;
import com.douban.eggshell.util.FileReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EggshellApplication.class)
public class MovieMapperTest {

    @Autowired
    MovieMapper movieMapper;

    @Test
    public void addMovie() {
        List<String> skipStrs = new ArrayList<>();
        skipStrs.add("IMDb");
        skipStrs.add("剧情简介");
//        FileReaderUtil.initReader("D:\\Chrysanthes\\Documents\\中软\\filmsDetail.txt", skipStrs);
        int count = 0;
        Movie m = null;
        List<String> objList = FileReaderUtil.getRowObject();
        while (objList != null) {
            m = new Movie();
            m.setName(objList.get(0));
            m.setImgurl("https:" + objList.get(1));
            m.setDirector(objList.get(2));
            m.setScriptwriter(objList.get(3));
            m.setActor(objList.get(4));
            m.setStyle(objList.get(5));
            m.setArea(objList.get(6));
            m.setLanguage(objList.get(7));
            m.setRelease_date(objList.get(8));
            m.setRunning_time(objList.get(9));
            m.setAlias(objList.get(10));
            m.setIntroduction(objList.get(11));

            m.setCreatetime(DateUtil.dataToString(new Date()));
            //放进数据库
            if (movieMapper.addMovie(m) > 0) {
                count++;
                System.out.println(count + "行，ok");
            } else {
                System.out.println("失败！！！！！");
            }
            //继续获取新对象
            objList = FileReaderUtil.getRowObject();
        }
        System.out.println("all完成");
    }

    @Test
    public void rankingByGrade() {
//        List<Movie> movies =  movieMapper.rankingByGrade(10);
//        if (movies!=null){
//            movies.forEach(System.out::println);
//
//        }
    }
}