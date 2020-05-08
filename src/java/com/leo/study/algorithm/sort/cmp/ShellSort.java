package com.leo.study.algorithm.sort.cmp;

import com.leo.study.algorithm.sort.Sort;

import java.util.ArrayList;
import java.util.List;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {

    protected void sort() {
        List<Integer> stepSequence = shellStepSequence();
        for (int step : stepSequence) {
            sort(step);
        }
    }

    private void sort(int step){
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin += step) {
                int curr = begin;
                while (curr > col && cmp(curr, curr - step) < 0) {
                    swap(curr, curr - step);
                    curr -= step;
                }
            }
        }
    }

    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step = step >> 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }
}
