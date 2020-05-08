package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

public class RadixSort  extends Sort<Integer> {

    @Override
    protected void sort() {
        // 寻找最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        for (int i = 1; i <= max; i *= 10) {
            countingSort(i);
        }
    }

    private void countingSort(int divider){
        int[] counts = new int[10];
        for (int i = 0; i < array.length; i++) {
            counts[array[i] / divider % 10]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] / divider % 10]] = array[i];
        }
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
