package com.leo.study.test.thread;

/**
 * @author: liuyanhui
 * @date: 2020/10/14
 */
public interface RejectPolicy {
    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}
