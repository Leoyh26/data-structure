package com.leo.study.pattern.test;

public class Main {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ExecuteThread());
        Thread t2 = new Thread(new ExecuteThread());
        t1.start();
        t2.start();
        System.out.println("execute end");

        // LazyInnerClassSingleton lazy = LazyInnerClassSingleton.getInstance();
    }
}
