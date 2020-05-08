package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    private T[] leftArray;

    protected void sort() {
        leftArray = (T[])new Comparable[array.length >> 1];
        sort(0, array.length - 1);
        // sort2(0, array.length);
    }

    private void sort(int begin, int end){
        if (end - begin < 1) {
            return;
        }
        int mid = (begin + end + 1) >> 1;
        sort(begin, mid - 1);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin - 1;
        int ri = mid, re = end;
        int ai = begin;
        // 拷贝左边的数组
        for (int i = li; i <= le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li <= le) {
            if (ri <= re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }

    // 采用左闭右开的形式
    private void sort2(int begin, int end){
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort2(begin, mid);
        sort2(mid, end);
        merge02(begin, mid, end);
    }

    private void merge02(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;
        // 将左侧数组复制
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }
}
