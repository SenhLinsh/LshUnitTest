package com.linsh.lshunittest;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.util.SparseArray;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 测试:
 * 1.测试在ViewHolder中使用SparseArray的性能损耗
 * 2.测试SparseArray对比HashMap的性能优势
 * <p>
 * 结论:
 * 1.使用SparseArray比使用字段来承载View的效率要低, 约为View数量的倍数(猜的)
 * 2.对于布局复杂, ViewHolder承载的View较多的RecyclerView, 建议使用ViewHolder字段来承载View
 * 3.对于布局简单, RecyclerView条目较少的, 可以使用可以重用的ViewHolder简化代码
 * 4.对于性能优化要求高的, 建议使用ViewHolder字段来承载View
 */
@RunWith(AndroidJUnit4.class)
public class SimplifiedViewHolderTest {

    @Test
    public void testListGetView() {
        int runTimes = 100000;
        int testHashMap = testHashMap(runTimes);
        int testSparseArray = testSparseArray(runTimes);
        int testArrayList = testArrayList(runTimes);
        int testViewHolder = testViewHolder(runTimes);

        Log.i("LshLog", "testHashMap: " + testHashMap);
        Log.i("LshLog", "testSparseArray: " + testSparseArray);
        Log.i("LshLog", "testArrayList: " + testArrayList);
        Log.i("LshLog", "testViewHolder: " + testViewHolder);

        // runtime=100000
        // LshLog: testHashMap: 515
        // LshLog: testSparseArray: 382
        // LshLog: testArrayList: 65
        // LshLog: testViewHolder: 30
    }

    public int testHashMap(int runTimes) {
        HashMap<Integer, String> sparseArray = new HashMap<>();
        sparseArray.put(0, "a");
        sparseArray.put(1, "b");
        sparseArray.put(2, "c");
        sparseArray.put(3, "d");
        sparseArray.put(4, "e");
        sparseArray.put(5, "f");
        sparseArray.put(6, "g");
        sparseArray.put(7, "h");
        sparseArray.put(8, "j");
        sparseArray.put(9, "k");

        long start = System.currentTimeMillis();
        for (int i = 0; i < runTimes; i++) {
            String result0 = sparseArray.get(0);
            String result1 = sparseArray.get(1);
            String result2 = sparseArray.get(2);
            String result3 = sparseArray.get(3);
            String result4 = sparseArray.get(4);
            String result5 = sparseArray.get(5);
            String result6 = sparseArray.get(6);
            String result7 = sparseArray.get(7);
            String result8 = sparseArray.get(8);
            String result9 = sparseArray.get(9);
        }
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }

    public int testSparseArray(int runTimes) {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(0, "a");
        sparseArray.put(1, "b");
        sparseArray.put(2, "c");
        sparseArray.put(3, "d");
        sparseArray.put(4, "e");
        sparseArray.put(5, "f");
        sparseArray.put(6, "g");
        sparseArray.put(7, "h");
        sparseArray.put(8, "j");
        sparseArray.put(9, "k");

        long start = System.currentTimeMillis();
        for (int i = 0; i < runTimes; i++) {
            String result0 = sparseArray.get(0);
            String result1 = sparseArray.get(1);
            String result2 = sparseArray.get(2);
            String result3 = sparseArray.get(3);
            String result4 = sparseArray.get(4);
            String result5 = sparseArray.get(5);
            String result6 = sparseArray.get(6);
            String result7 = sparseArray.get(7);
            String result8 = sparseArray.get(8);
            String result9 = sparseArray.get(9);
        }
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }

    public int testArrayList(int runTimes) {
        ArrayList<String> sparseArray = new ArrayList<>();
        sparseArray.add("a");
        sparseArray.add("b");
        sparseArray.add("c");
        sparseArray.add("d");
        sparseArray.add("e");
        sparseArray.add("f");
        sparseArray.add("g");
        sparseArray.add("h");
        sparseArray.add("j");
        sparseArray.add("k");

        long start = System.currentTimeMillis();
        for (int i = 0; i < runTimes; i++) {
            String result0 = sparseArray.get(0);
            String result1 = sparseArray.get(1);
            String result2 = sparseArray.get(2);
            String result3 = sparseArray.get(3);
            String result4 = sparseArray.get(4);
            String result5 = sparseArray.get(5);
            String result6 = sparseArray.get(6);
            String result7 = sparseArray.get(7);
            String result8 = sparseArray.get(8);
            String result9 = sparseArray.get(9);
        }
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }

    public int testViewHolder(int runTimes) {
        ViewHolder viewHolder = new ViewHolder();

        long start = System.currentTimeMillis();
        for (int i = 0; i < runTimes; i++) {
            String result0 = viewHolder.a;
            String result1 = viewHolder.b;
            String result2 = viewHolder.c;
            String result3 = viewHolder.d;
            String result4 = viewHolder.e;
            String result5 = viewHolder.f;
            String result6 = viewHolder.g;
            String result7 = viewHolder.h;
            String result8 = viewHolder.j;
            String result9 = viewHolder.k;
        }
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }

    public class ViewHolder {
        private String a = "a";
        private String b = "b";
        private String c = "c";
        private String d = "d";
        private String e = "e";
        private String f = "f";
        private String g = "g";
        private String h = "h";
        private String j = "j";
        private String k = "k";
    }
}
