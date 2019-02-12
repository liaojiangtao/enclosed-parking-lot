package com.fangle.parking.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    //字符类时间转Date
    public static Date StringToDate(String stringDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(stringDate);
    }
}
