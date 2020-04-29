package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

/**
 * @date: 2020/4/26
 */
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {

    // 冒泡排序
    // 每一次分别取相邻的两个元素进行比较  如果前面的元素较大  则向后交换

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int i = 1; i <= end; i++) {
                if (cmp(i, i - 1) < 0) {
                    swap(i, i - 1);
                }
            }
        }
    }
}
