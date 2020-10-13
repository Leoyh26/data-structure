package com.leo.study.test;

import sun.misc.Launcher;

/**
 * @author: liuyanhui
 * @date: 2020/8/21
 */
public class TestLoad {

    public static void main(String[] args) {
        System.out.println("输出的打印语句");
    }

    public TestLoad(){
        Launcher launcher;
        System.out.println("构造方法");
        System.out.println("我是熊孩子我的智商=" + ZhiShang +",情商=" + QingShang);
    }

    {
        System.out.println("普通代码块");
    }

    int ZhiShang = 250;
    static int QingShang = 666;

    static
    {
        System.out.println("静态代码块");
    }
}
