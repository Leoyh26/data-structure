package com.leo.study.test;

/**
 * @author: liuyanhui
 * @date: 2020/7/20
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(tableSizeFor(8));
    }

    private static int tableSizeFor(int capcity){
        int n = capcity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }
}
