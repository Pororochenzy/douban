package com.douban.eggshell.util;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileReaderUtilTest {

    @Test
    public void getLine() {
        List<String> skipStrs = new ArrayList<>();
        skipStrs.add("IMDb");
        skipStrs.add("剧情简介");
        FileReaderUtil.initReader("D:\\Chrysanthes\\Documents\\中软\\filmsDetail.txt", skipStrs);
        for (int i = 0; i < 1; i++) {
            List<String> str = FileReaderUtil.getRowObject();
            if (str != null) {
                str.forEach(System.out::println);
                System.out.println();
            }
        }
    }

    @Test
    public void getLine1() {
        List<String> skipStrs = new ArrayList<>();
        skipStrs.add("IMDb");
        skipStrs.add("剧情简介");
        skipStrs.add("导演剪辑");

        FileReaderUtil.initReader("C:\\Users\\chen\\Desktop\\豆瓣爬取\\filmsDetail.txt", skipStrs);
        FileReaderUtil.checkFileMatch(12);
    }
}