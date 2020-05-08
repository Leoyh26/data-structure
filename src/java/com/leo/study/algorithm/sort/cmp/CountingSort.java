package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

public class CountingSort extends Sort<Integer> {


    @Override
    protected void sort() {
        // 寻找最值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 开辟内存空间存储次数
        int[] counts = new int[max - min + 1];
        // 存储出现次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        // 存储累计次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 开辟内存空间存储有序数组
        int[] newArray = new int[array.length];
        // 倒叙遍历数组 按序存储
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] - min]] = array[i];
        }
        // 将有序数组复制到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }

    protected void sort02() {
        // 寻找最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 开辟有序数组记录次数
        int[] counts = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
                array[index++] = i;
            }
        }
    }
}
