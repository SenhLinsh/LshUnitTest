package com.linsh.lshunittest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.linsh.lshutils.others.RandomUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 测试:
 * 1. SharedPreferences 在存储大量键值对时对查询的影响
 * <p>
 * 结论:
 * 1. 当 putCount 上到 7000 之后压力就储存速度就很慢了, 很难达到 10000
 * 2. 循环里获取多个比获取单个所用的时间不会多多少, 甚至在循环获取1000个时比获取一个用时还少.
 * 3. 储存的键值对个数对查询用时的影响很大, 查询次数(循环内)影响较小
 * 4. 当存储的键值对达到 100 时, 单次查询的用时将达到 5-8 ms. 键值对 1000 时, 达到 40-60 ms.
 * 5. 初始化过 Editor 之后, 单次查询的速度基本为 0-1 ms
 *
 */
@RunWith(AndroidJUnit4.class)
public class SharedPreferenceTest {

    @Test
    public void testGetString() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedPreferences sp = appContext.getSharedPreferences("LshSharedPreferences", 0);

        int putCount = 1000;
        int putStartNum = 0;
        int getCount = 1;
        int getStartNum = 0;

        long putStrings = putStrings(sp, putCount, putStartNum);
        Log.i("LshTag", "putStrings cost time = " + putStrings + "ms");

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long getStrings = getStrings(sp, getCount, getStartNum);
        Log.i("LshTag", "getStrings cost time = " + getStrings + "ms");
        Assert.assertEquals(getStrings, -1);
    }

    public long getStrings(SharedPreferences sp, int count, int startNum) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String key = "key" + RandomUtils.getRandom(startNum, startNum + count - 1);
            String value = sp.getString(key, "");
            System.err.println("getStrings --> " + key + " : " + value);
        }
        return System.currentTimeMillis() - start;
    }

    public long putStrings(SharedPreferences sp, int count, int startNum) {
        long start = System.currentTimeMillis();
        for (int i = startNum; i < startNum + count; i++) {
            String key = "key" + i;
            String value = "value" + i;
            sp.edit().putString(key, value).commit();
            System.err.println("putStrings --> " + key + " : " + value);
        }
        return System.currentTimeMillis() - start;
    }
}
