package com.leo.study.algorithm.sort;

import com.leo.study.algorithm.sort.cmp.*;
import com.leo.study.tools.Asserts;
import com.leo.study.tools.Integers;

import java.util.Arrays;

/**
 * @date: 2020/4/26
 */
public class SortTest {

    public static void main(String[] args) {
        // Integer[] array = Integers.random(10000, 1, 100000);
        Integer[] array = {9, 20, 13, 6, 30, 13, 129, 36, 360};

                testSorts(array,
            new RadixSort()
            /* new CountingSort(),
            new SelectionSort(),
            new BubbleSort1(),
            new BubbleSort2(),
            new BubbleSort3(),
            new HeapSort(),
            new QuickSort(),
            new InsectionSort3(),
            new InsectionSort2(),
            new InsectionSort1(),
            new MergeSort(),
            new ShellSort()*/
        );
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Integers.println(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }

    }

    static void testBinarySearch(int num){
        Integer[] array = Integers.ascOrder(1, 100);
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (num <= mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // System.out.println(left);
        if (left == num) {
            System.out.println("find the num: " + num + " int the array: " + left);
        } else {
            System.out.println("not find the num");
        }
    }
}
