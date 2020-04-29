package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

/**
 * @date: 2020/4/26
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

    // 优化：尾部可能已经排序好  所以记录最后交换的位置  之后的交换后面的就无需再比

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int endIndex = 1;
            for (int i = 1; i <= end; i++) {
                if (cmp(i, i - 1) < 0) {
                    swap(i, i - 1);
                    endIndex = i;
                }
            }
            end = endIndex;
        }
    }
}
