package com.leo.study.lock.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author: liuyanhui
 * @date: 2020/7/23
 */
public class WriteAndRead {

    private Semaphore rCountMetux;
    private Semaphore wDateMetux;
    private Semaphore flag;
    private int rcount = 0;

    public void write() throws Exception {
        while (true) {
            flag.acquire();
            wDateMetux.acquire();
            // 写数据
            wDateMetux.release();
            flag.release();
        }
    }

    public void read() throws Exception {
        while (true) {
            flag.acquire();
            rCountMetux.acquire();
            if (rcount == 0) {
                wDateMetux.acquire();
            }
            rcount++;
            rCountMetux.release();
            flag.release();
            // 读取数据
            rCountMetux.acquire();
            rcount--;
            if (rcount == 0) {
                wDateMetux.release();
            }
            rCountMetux.release();
        }
    }
}
