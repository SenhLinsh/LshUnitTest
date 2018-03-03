package com.linsh.lshunittest.util;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/06
 *    desc   :
 * </pre>
 */
public class LeakUtils {

    private static List<WeakReference<Object>> list = new LinkedList<>();


    public static void observeObject(Object obj) {
        list.add(new WeakReference<Object>(obj));
    }

    public static int totalSize() {
        return list.size();
    }

    public static int actualSize() {
        int size = 0;
        for (WeakReference<Object> reference : list) {
            Object obj = reference.get();
            if (obj != null) {
                size++;
            }
        }
        return size;
    }

    public static void clear() {
        list.clear();
    }
}
