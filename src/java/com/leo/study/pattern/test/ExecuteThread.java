package com.leo.study.pattern.test;

import com.leo.study.pattern.singleton.LazyInnerClassSingleton;

public class ExecuteThread implements Runnable {

    public void run() {
        LazyInnerClassSingleton lazySingleton = LazyInnerClassSingleton.getInstance();
        // LazyDoubleCheckSingleton lazySingleton = LazyDoubleCheckSingleton.getInstance();
        // LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " : " + lazySingleton);
    }
}
