package com.leo.study.algorithm.sort;

import com.leo.study.algorithm.sort.cmp.BubbleSort1;
import com.leo.study.algorithm.sort.cmp.BubbleSort2;
import com.leo.study.algorithm.sort.cmp.BubbleSort3;
import com.leo.study.algorithm.sort.cmp.HeapSort;
import com.leo.study.algorithm.sort.cmp.InsectionSort1;
import com.leo.study.algorithm.sort.cmp.InsectionSort2;
import com.leo.study.algorithm.sort.cmp.SelectionSort;
import com.leo.study.tools.Asserts;
import com.leo.study.tools.Integers;

import java.util.Arrays;

/**
 * @date: 2020/4/26
 */
public class SortTest {

    public static void main(String[] args) {
        Integer[] array = Integers.random(10000, 1, 100000);

        testSorts(array,
            new SelectionSort(),
            new BubbleSort1(),
            new BubbleSort2(),
            new BubbleSort3(),
            new HeapSort(),
            new InsectionSort2(),
            new InsectionSort1()
        );
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
