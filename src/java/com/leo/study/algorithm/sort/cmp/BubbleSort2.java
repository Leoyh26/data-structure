package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

/**
 * @date: 2020/4/26
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

    // 优化：数组可能再某次循环中就已经有序  记录每次循环中是否发生了交换  如果未发生交换  证明有序

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int i = 1; i <= end; i++) {
                if (cmp(i, i - 1) < 0) {
                    swap(i, i - 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
