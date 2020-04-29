package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

/**
 * @date: 2020/4/26
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    // 每回先找出未排序数组中的最大值 然后同最后位置的元素进行交换

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int i = 1; i <= end; i++) {
                if (cmp(i, maxIndex) > 0) {
                    maxIndex = i;
                }
            }
            swap(maxIndex, end);
        }
    }
}
