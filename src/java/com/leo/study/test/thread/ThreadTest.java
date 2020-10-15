package com.leo.study.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liuyanhui
 * @date: 2020/10/10
 */
public class ThreadTest extends Thread {

    private static int count = 0;

    @Override
    public synchronized void run() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("线程被中断，程序退出");
                    return;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出");
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
