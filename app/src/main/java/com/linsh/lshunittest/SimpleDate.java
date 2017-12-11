package com.linsh.lshunittest;

import java.util.Date;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 简单化的日期表示
 * </pre>
 */
public class SimpleDate {

    private int[] mDate;

    public SimpleDate(long date) {
        this(new Date(date));
    }

    public SimpleDate(Date date) {
        this(date, false);
    }

    public SimpleDate(Date date, boolean isLunar) {
        this(date.getYear() + 1900, date.getMonth() + 1, date.getDate(), isLunar);
    }

    public SimpleDate(int year, int month, int day) {
        this(year, month, day, false);
    }

    public SimpleDate(int year, int month, int day, boolean isLunar) {
        mDate = new int[3];
        mDate[0] = year;
        mDate[1] = month;
        mDate[2] = day;
    }

    public int getYear() {
        return mDate[0];
    }

    public SimpleDate setYear(int year) {
        mDate[0] = year;
        return this;
    }

    public int getMonth() {
        return mDate[1];
    }

    public SimpleDate setMonth(int month) {
        mDate[1] = month;
        return this;
    }

    public int getDay() {
        return mDate[2];
    }

    public SimpleDate setDay(int day) {
        mDate[2] = day;
        return this;
    }

    public Date getDate() {
        return new Date(mDate[0] - 1900, mDate[1] - 1, mDate[2]);
    }

    public String getNormalizedString() {
        return getNormalizedString(true);
    }

    public String getNormalizedString(boolean hasYear) {
        return getNormalizedStr(hasYear ? mDate[0] : 0, mDate[1], mDate[2]);
    }

    public String getDisplayString() {
        return getDisplayString(true);
    }

    public String getDisplayString(boolean hasYear) {
        return getDisplayStr(hasYear ? mDate[0] : 0, mDate[1], mDate[2]);
    }

    public static String getDisplayStr(Date date, boolean hasYear) {
        return getDisplayStr(hasYear ? date.getYear() + 1900 : 0, date.getMonth() + 1, date.getDate());
    }

    private static String getDisplayStr(int year, int month, int day) {
        StringBuilder builder = new StringBuilder();
        if (year > 0) {
            builder.append(year).append('年');
        }
        builder.append(month).append('月')
                .append(day).append('日');
        return builder.toString();
    }

    public static String getNormalizedStr(int year, int month, int day) {
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
        builder.append(day);
        return builder.toString();
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
