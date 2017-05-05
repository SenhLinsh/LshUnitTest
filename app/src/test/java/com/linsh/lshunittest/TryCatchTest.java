package com.linsh.lshunittest;

import org.junit.Test;

/**
 * 测试:
 * 1.try-catch对运行速度的损耗
 * <p>
 * 结论:
 * 1.可以看出, if语句以及try-catch没有捕捉到异常时, 运行时间几乎是差不多的; 而进行catch异常捕捉时, 则消耗了很多的时间.
 * 2.项目中不建议使用catch来进行逻辑判断或传递数据, 异常的捕捉对性能影响很大. 但是可以经常使用try-catch来对真实的异常进行捕捉, 不发生异常时try语句几乎不影响性能
 */
public class TryCatchTest {
    @Test
    public void testAll() throws Exception {
        int times = 1000000;
        int testNormal = testNormal(times);
        int testIf = testIf(times);
        int testTry = testTry(times);
        int testCatch = testCatch(times);

        System.err.println("testNormal : " + testNormal);
        System.err.println("testIf : " + testIf);
        System.err.println("testTry : " + testTry);
        System.err.println("testCatch : " + testCatch);

        // times = 1000000 时
        // testNormal : 5
        // testIf : 8
        // testTry : 4
        // testCatch : 600
    }

    public int testNormal(int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {

            int a = 0 / 1;

        }
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public int testIf(int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {

            if (true) {
                int a = 0 / 1;
            }

        }
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public int testTry(int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {

            try {
                int a = 0 / 1;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public int testCatch(int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {

            try {
                int a = 1 / 0;
            } catch (Exception e) {
            }

        }
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

}