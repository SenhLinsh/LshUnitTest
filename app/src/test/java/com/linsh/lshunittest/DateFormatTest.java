package com.linsh.lshunittest;

import com.linsh.lshunittest.util.TestUtils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/30
 *    desc   : 测试 SimpleDateFormat 格式化日期成字符串与常规解析的运行时间
 *    结果    : 1. DateFormatTest 的耗时循环比常规的耗时要多, 但不会差距只有几倍的时间
 *             2. DateFormatTest 的单次运行时间比常规的耗时要多得多, 可能是需要初始化某些对象
 *
 * </pre>
 */
public class DateFormatTest {

    @Test
    public void testDate() {
        final Date date = new Date();
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);
        getDateTimeStr(date);
        long dateFormat = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);
            }
        }, 10000);
        long normalFormat = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                getDateTimeStr(date);
            }
        }, 10000);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);
        String normalizedString = getDateTimeStr(date);
        TestUtils.print("format = " + format + "    normalizedString = " + normalizedString);
        TestUtils.printLn();
        TestUtils.print("dateFormat = " + dateFormat + "    normalFormat = " + normalFormat);
    }

    @Test
    public void testWeekDay() {
        final Date date = new Date();
        long dateFormat = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                new SimpleDateFormat("E", Locale.CHINA).format(date);
            }
        }, 10000);
        long normalFormat = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                getWeekDayString(date, "星期");
            }
        }, 10000);
        TestUtils.print("dateFormat = " + dateFormat + "    normalFormat = " + normalFormat);
    }

    public static String getWeekDayString(Date date, String prefix) {
        if (date == null || prefix == null) return "";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayIndex) {
            case Calendar.SUNDAY:
                prefix += "日";
                break;
            case Calendar.MONDAY:
                prefix += "一";
                break;
            case Calendar.TUESDAY:
                prefix += "二";
                break;
            case Calendar.WEDNESDAY:
                prefix += "三";
                break;
            case Calendar.THURSDAY:
                prefix += "四";
                break;
            case Calendar.FRIDAY:
                prefix += "五";
                break;
            case Calendar.SATURDAY:
                prefix += "六";
                break;
        }
        return prefix;
    }

    public static String getDateTimeStr(Date date) {
        return getDateTimeStr(date.getYear() + 1900, date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
    }

    public static String getDateTimeStr(int year, int month, int day, int hour, int min, int sec) {
        StringBuilder builder = new StringBuilder();
        if (year > 0) {
            builder.append(year).append('-');
        }
        if (month < 10) {
            builder.append('0');
        }
        builder.append(month).append('-');
        if (day < 10) {
            builder.append('0');
        }
        builder.append(day).append(' ');
        if (hour < 10) {
            builder.append('0');
        }
        builder.append(hour).append(':');
        if (min < 10) {
            builder.append('0');
        }
        builder.append(min).append(':');
        if (sec < 10) {
            builder.append('0');
        }
        builder.append(sec);
        return builder.toString();
    }
}
