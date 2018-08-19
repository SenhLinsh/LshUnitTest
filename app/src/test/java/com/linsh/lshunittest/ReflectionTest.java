package com.linsh.lshunittest;

import com.linsh.lshunittest.util.TestUtils;

import org.junit.Test;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/07/14
 *    desc   : 测试: java 反射的性能损耗
 *
 * </pre>
 */
public class ReflectionTest {

    @Test
    public void reflect() {
        int times = 1000;

        long newObj = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                TestObj obj = new TestObj();
            }
        }, times);
        long reflectObj = TestUtils.getRunTime(new Runnable() {
            @Override
            public void run() {
                try {
                    TestObj obj = TestObj.class.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, times);

        System.out.println("newObj = " + newObj);
        System.out.println("reflectObj = " + reflectObj);
    }

    public static class TestObj {

        public TestObj() {
        }
    }
}
