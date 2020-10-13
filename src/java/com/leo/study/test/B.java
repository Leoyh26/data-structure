package com.leo.study.test;

/**
 * @author: liuyanhui
 * @date: 2020/8/20
 */
public class B extends A {
    public static B t1 = new B();
    public static B t2 = new B();
    public B() {
        System.out.println("B构造块");
    }
    static
    {
        System.out.println("B静态块");
    }

    public static void main(String[] args) {
        new B();
        System.out.println("===================");
        new B();
        System.out.println("===================");
        new B();
        System.out.println("===================");
        new B();
    }
}

class A{
    {
        System.out.println("A普通代码块");
    }
    public A() {
        System.out.println("A构造块");
    }
    static {
        System.out.println("A静态块");
    }
}
