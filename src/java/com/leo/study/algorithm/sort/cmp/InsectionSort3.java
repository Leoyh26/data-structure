package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

public class InsectionSort3<T extends Comparable<T>> extends Sort<T> {


    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            insert(i, search(i));
        }
    }

    private int search (int index) {
        int left = 0;
        int right = index;
        // 利用二分法 查找到第一个大于array[i]的位置
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (cmp(index, mid) < 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private void insert(int source, int dest){
        T value = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i-1];
        }
        array[dest] = value;
    }
}
