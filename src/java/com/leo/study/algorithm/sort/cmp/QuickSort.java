package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    protected void sort() {
        sort(0, array.length - 1);
    }

    private void sort(int begin, int end){
        if (end - begin < 1) {
            return;
        }
        int pivot = pivotIndex(begin, end);
        sort(begin, pivot - 1);
        sort(pivot + 1, end);
    }

    private int pivotIndex(int begin, int end){
        // 随机选取元素和begin交换 随机选取轴点元素
        swap(begin, begin + (int) Math.random() * (end + 1 - begin));
        // 备份pivot
        T pivot = array[begin];
        while (begin < end) {
            // 从end向前 相等变换方向
            while (begin < end) {
                if (cmp(array[end], pivot) > 0) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }

            // 从begin向后 相等变换方向
            while (begin < end) {
                if (cmp(array[begin], pivot) < 0) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
                break;
            }
        }
        // 替换
        array[begin] = pivot;
        return begin;
    }
}
