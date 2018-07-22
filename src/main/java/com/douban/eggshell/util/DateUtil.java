package com.douban.eggshell.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static  String dataToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        return  strDate;
    }
}
