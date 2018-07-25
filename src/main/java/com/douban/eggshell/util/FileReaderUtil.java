package com.douban.eggshell.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileReaderUtil {

    private static BufferedReader reader;
    private static List<String> skipList;
    private static int lineCount;

    /**
     * 初始化Reader
     *
     * @param filePath 文件全路径
     * @param skipStrs 要过滤掉的无用行标志字符串列表
     */
    public static void initReader(String filePath, List<String> skipStrs) {
        try {
            reader = new BufferedReader(new FileReader(filePath));
            skipList = skipStrs;
            lineCount = 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 判断文件中所有数据是否统一格式，即检查是否存在缺行
     *
     * @param rowNum 一份应该有几行（不包括无用行）
     */
    public static void checkFileMatch(int rowNum) {
        int objectCount = 0, flag = 0;
        List<String> objectList = getRowObject();
        while (objectList != null) {
            objectCount++;
            if (objectList.size() != rowNum) {
                //缺行
                flag = 1;
                String objLine1 = "";
                if (objectList.size() > 0) {
                    objLine1 = objectList.get(0);
                }
                System.out.println(String.format("第%d个对象[包含'%s'等...]有问题，在第%d行左右，该对象缺%d行数据",
                        objectCount, objLine1, lineCount - 1, rowNum - objectList.size()));
            }
            objectList = getRowObject();
        }
        if (flag == 0 && lineCount != 0) {
            System.out.println(String.format("完成！不存在缺行，共%d个对象，%d行", objectCount, lineCount));
        }
    }

    /**
     * 获取一个对象的完整数据列表
     *
     * @return list
     */
    public static List<String> getRowObject() {
        //自动根据文件内的分割符获取一个完整对象的所有数据
        String line = getLine(":");
        if (line == null)
            return null;
        List<String> rowObject = new ArrayList<>();
        while (!line.startsWith("=====")) {
            //System.out.println(line);
            rowObject.add(line);
            line = getLine(":");
        }
        if (rowObject.size() != 0) {
            return rowObject;
        }
        return null;
    }

    /**
     * 向下读取一行
     *
     * @return String
     */
    public static String getLine() {
        //向下获取一行
        try {
            String line = null;
            if (reader != null && reader.ready()) {
                line = reader.readLine();
                lineCount++;
            }
            if (line != null) {
                while (isUselessLine(line)) {
                    //无用行，自动再读一行
                    line = reader.readLine();
                    lineCount++;
                }
                //System.out.println(line);
                return line.trim();
            } else {
                if (reader != null) {
                    //读完，关闭流
                    reader.close();
                    System.out.println("文件读取完毕，已关闭");
                } else {
                    System.out.println("请先调用initReader()方法初始化Reader！");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 向下读取一行，且只返回指定标志后面的部分
     *
     * @param dealKey 指定标志
     * @return String
     */
    public static String getLine(String dealKey) {
        //获取新行，并截取指定key后的部分
        String line = getLine();
        if (line != null) {
            int index = line.indexOf(dealKey);
            if (index == -1)
                return line;
            return line.substring(index + 1).trim();
        }
        return null;
    }

    /**
     * 判断是否无用行，根据初始化时所给的过滤列表而定，以及空行
     *
     * @param line 要进行判断的行字符串
     * @return bool
     */
    public static boolean isUselessLine(String line) {
        if (line != null) {
            if (line.isEmpty()) {
                return true;
            }
            if (skipList != null) {
                for (String str : skipList) {
                    if (line.startsWith(str) || line.endsWith(str)) {
                        //逐一过滤，跳过包含指定字符串的行
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static double getGrade() {
        //无评分数据，瞎鸡儿生成
        Random rand = new Random();
        double gradeD = rand.nextDouble();
        int gradeI = rand.nextInt(10);
        double grade = gradeD * gradeI;
        if (grade < 4) {
            grade += 4.5;
        }
        grade = (double) Math.round(grade * 10) / 10;
        return grade;
    }

    public static int getComment_num() {
        //无评分人数数据，瞎鸡儿生成
        Random rand = new Random();
        int comment_num = rand.nextInt(80000);
        if (comment_num < 1000) {
            comment_num += 1111;
        }
        return comment_num;
    }
}
