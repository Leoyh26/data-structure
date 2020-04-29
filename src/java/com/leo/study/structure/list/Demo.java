package com.leo.study.structure.list;

/**
 * @date: 2020/3/26
 */
public class Demo {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 10);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.toString());
    }

}
