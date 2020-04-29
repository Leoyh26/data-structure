package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

/**
 * @date: 2020/4/26
 */
public class InsectionSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int curr = i;
            T value = array[curr];
            while (curr > 0 && cmp(value, array[curr - 1]) < 0) {
                array[curr] = array[curr - 1];
                curr--;
            }
            array[curr] = value;
        }
    }
}
