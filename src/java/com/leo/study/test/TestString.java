package com.leo.study.test;

/**
 * @author: liuyanhui
 * @date: 2020/8/25
 */
public class TestString {

    public static void main(String[] args) {
        /*String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("java").toString();
        System.out.println(str2.intern() == str2);*/

        String s0 = "kvill";
        String s1 = "kvill";
        String s2 = "kvi" + "ll";

        System.out.println(s0 == s1);
        System.out.println(s0 == s2);
    }
}
