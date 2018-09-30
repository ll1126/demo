package com.test.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import static com.test.demo.core.constant.Const.YYYY_MM_DD_HH_MM_SS;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 给指定日期累加上指定的小时数
     *
     * @param time     日期参数
     * @param add_Hour 累加小时数(可为负数)
     * @return 累加后的日期
     */
    public static Date addHourByDay(Date time, int add_Hour) {

        if (time == null) {
            return new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.HOUR_OF_DAY, add_Hour);
        return cal.getTime();
    }

    /**
     * 时间格式转换
     *
     * @param date    日期参数
     * @param pattern 转换格式
     * @return 时间字符串
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String time = simpleDateFormat.format(date);
        return time;
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        System.out.println(sdf.format(addHourByDay(new Date(), -1)));
    }
}
