package com.linsh.lshunittest.leak;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.linsh.lshunittest.R;
import com.linsh.lshunittest.util.LeakUtils;
import com.linsh.utilseverywhere.RandomUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/06
 *    desc   : 测试 [持有Activity引用的匿名内部类 & 无引用的匿名内部类 & 内部类 & 静态内部类 & 被引用的静态内部类] 在 Activity 中创建后是否会造成内存泄露
 *             结果:
 *                  不管是内部类或者匿名内部类, 以及是否持有 Activity 的引用, 均会在内存不足的情况下会被销毁, 不会造成内存泄露
 *                  只有被其他不可被回收的对象(如 Activity)所引用的实例才无法被回收
 * </pre>
 */
public class LeakActivity extends AppCompatActivity {

    public List<Object> list = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_leak);
    }

    public void addAnonymousInnerClassInstance(View view) {
        for (int i = 0; i < 1000; i++) {
            Runnable obj = new Runnable() {
                private String str = RandomUtils.getNumberAndLetter(100);
                private Activity mActivity;

                @Override
                public void run() {
                    mActivity = LeakActivity.this;
                }
            };
            LeakUtils.observeObject(obj);
        }
    }

    public void addAnonymousInnerClassInstance2(View view) {
        for (int i = 0; i < 1000; i++) {
            LeakUtils.observeObject(new Runnable() {
                private String str = RandomUtils.getNumberAndLetter(100);

                @Override
                public void run() {
                }
            });
        }
    }

    public void addInnerClassInstance(View view) {
        for (int i = 0; i < 1000; i++) {
            LeakUtils.observeObject(new MyInnerRunnable());
        }
    }

    public void addReferencedNormalInstance(View view) {
        for (int i = 0; i < 1000; i++) {
            MyRunnable obj = new MyRunnable();
            LeakUtils.observeObject(obj);
            list.add(obj);
        }
    }

    public void addNormalInstance(View view) {
        for (int i = 0; i < 1000; i++) {
            MyRunnable obj = new MyRunnable();
            LeakUtils.observeObject(obj);
        }
    }

    public void gc(View view) {
        System.gc();
    }

    public void checkCount(View view) {
        int counts = LeakUtils.actualSize();
        Toast.makeText(this, "counts:" + counts, Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {
        LeakUtils.clear();
    }

    public static class MyInnerRunnable implements Runnable {

        private String str = RandomUtils.getNumberAndLetter(100);

        @Override
        public void run() {
        }
    }

    public static class MyRunnable implements Runnable {

        private String str = RandomUtils.getNumberAndLetter(100);

        @Override
        public void run() {
        }
    }
}
