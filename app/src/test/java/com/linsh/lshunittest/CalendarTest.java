package com.linsh.lshunittest;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/30
 *    desc   : 测试 Calendar 获取日期与 Date 获取日期的效率比较
 *             结果: Calendar < Date
 *
 *                  Calendar.getInstance() 每次都会得到一个新的 Calendar 对象, 不会重用
 *                  Calendar 对比 Date 的优势在于可以国际化
 * </pre>
 */
public class CalendarTest {

    @Test
    public void test() {
        long calendar = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                final Calendar instance = Calendar.getInstance();
                int year = instance.get(Calendar.YEAR);
                int month = instance.get(Calendar.MONTH);
                int day = instance.get(Calendar.DAY_OF_MONTH);
            }
        }, 100000);
        long dateTime = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                final Date date = new Date();
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDate();
            }
        }, 100000);
        TestUtils.printLn("calendar = " + calendar + "    date = " + dateTime);
    }
}
