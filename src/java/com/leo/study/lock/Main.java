package com.leo.study.lock;

import java.util.Objects;

/**
 * @author: liuyanhui
 * @date: 2020/7/27
 */
public class Main {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();
    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        // deadLock();
        testVolatile();
    }

    private static void testVolatile() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        System.out.println("thread start");
        Thread.sleep(1000L);
        stop = true;
    }

    private static void deadLock(){
        new Thread(() -> {
            synchronized (lockA) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + " get lockB");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lockB) {
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + " get lockA");
                }
            }
        }).start();
    }
}
