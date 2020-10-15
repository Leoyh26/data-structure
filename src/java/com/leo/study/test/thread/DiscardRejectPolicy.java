package com.leo.study.test.thread;

/**
 * @author: liuyanhui
 * @date: 2020/10/14
 */
public class DiscardRejectPolicy implements RejectPolicy {

    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        // do nothing
        System.out.println("discard one task");
    }
}
