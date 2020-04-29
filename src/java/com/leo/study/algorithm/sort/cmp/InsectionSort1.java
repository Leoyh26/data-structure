package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

/**
 * @date: 2020/4/26
 */
public class InsectionSort1<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int curr = i;
            while (curr > 0 && cmp(curr, curr - 1) < 0) {
                swap(curr, curr - 1);
                curr--;
            }
        }
    }
}
