package com.douban.eggshell.util;

import java.util.ArrayList;
import java.util.List;

public class StrUtil {

    /**
     * 连接两个字符串
     */
    public static String strConcat(String frontStr, String afterStr) {
        return frontStr.concat(afterStr);
    }

    /**
     * 在字符串后面添加分割符'/'
     */
    public static String strJoinDelimiter(String str) {
        return str.concat(" / ");
    }

    /**
     * 在字符串后面添加省略号
     */
    public static String strJoinEllipsis(String str) {
        return str.concat("...");
    }

    /**
     * 合并两个字符串，自动在两个字符串之间添加分隔符
     */
    public static String strMerge(String frontStr, String afterStr) {
        return strConcat(strJoinDelimiter(frontStr.trim()), afterStr.trim());
    }

    /**
     * 限制字符串字数在指定长度以内，超出自动截取并添加省略号'...'
     */
    public static String strLimitLen(String str, int len) {
        if (str.length() > len) {
            return strJoinEllipsis(str.substring(0, len - 3));
        }
        return str;
    }

    /**
     * 合并多个字符串（按list内先后顺序，自动添加分割符）
     */
    public static String strMergeArray(List<String> strList) {
        String frontStr = strList.get(0);
        for (int i = 1; i < strList.size(); i++) {
            frontStr = strMerge(frontStr, strList.get(i));
        }
        return frontStr.trim();
    }

    /**
     * 获取第一个分隔符前的一段字符串
     */
    public static String getFirstDelimiter(String str) {
        return getPartDelimiter(str, 1);
    }

    /**
     * 获取指定分隔符位置前的一段字符串
     */
    public static String getPartDelimiter(String str, int index) {
        String[] splitStrs = str.split("/", index + 1);
        if (splitStrs.length == 1 || splitStrs.length <= index) {
            return str.trim();
        }
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            strList.add(splitStrs[i]);
        }
        return strMergeArray(strList);
    }
}
