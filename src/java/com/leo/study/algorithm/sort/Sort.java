package com.leo.study.algorithm.sort;

import java.text.DecimalFormat;

/**
 * @date: 2020/4/26
 */
public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>> {
    protected T[] array;
    private int swapCount;
    private int cmpCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(T[] array){
        if (array == null || array.length <= 1) {
            return;
        }
        this.array = array;
        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    public int compareTo(Sort<T> o) {
        int result = (int) (time - o.time);
        if (result != 0) {
            return result;
        }
        result = cmpCount - o.cmpCount;
        if (result != 0) {
            return result;
        }

        return swapCount - o.swapCount;
    }

    protected abstract void sort();

    protected void swap(int i1, int i2) {
        swapCount++;
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    /**
     * 返回值 result == 0  array[i1] == array[i2]
     * 返回值 result  > 0  array[i1]  > array[i2]
     * 返回值 result  < 0  array[i1]  < array[i2]
     * @param i1 值1
     * @param i2 值2
     * @return int
     */
    protected int cmp (int i1, int i2) {
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }

    protected int cmp(T v1, T v2) {
        cmpCount++;
        return v1.compareTo(v2);
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        return "【" + getClass().getSimpleName() + "】\n"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    private String numberString(int number) {
        if (number < 10000) {
            return "" + number;
        }

        if (number < 100000000) {
            return fmt.format(number / 10000.0) + "万";
        }
        return fmt.format(number / 100000000.0) + "亿";
    }
}
