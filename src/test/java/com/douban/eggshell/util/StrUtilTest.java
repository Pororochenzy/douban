package com.douban.eggshell.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StrUtilTest {

    @Test
    public void getFirstDelimiter() {
        String str = "2017-12-09(日本) / 2018-08(中国大陆) / 堺雅人 / 高畑充希";
        //        System.out.println(StrUtil.getFirstDelimiter(str));
//        String[] strs = str.split("/", 5);
//        System.out.println(strs.length);
//        for (String s : strs) {
//            System.out.println(s);
//        }
        System.out.println(StrUtil.getPartDelimiter(str,3));
    }
}