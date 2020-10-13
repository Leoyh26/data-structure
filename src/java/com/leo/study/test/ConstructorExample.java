package com.leo.study.test;

import java.sql.DriverManager;

/**
 * @author: liuyanhui
 * @date: 2020/8/25
 */
class Foo {
    int i = 1;

    Foo() {
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
        test();
    }

    {
        i = 2;
    }

    protected int getValue() {
        return i;
    }

    public void test(){
        System.out.println("调用了父类的方法");
    }

    public void fu(){
        System.out.println("父类特有方法");
    }
}

//子类
class Bar extends Foo {
    int j = 1;

    Bar() {
        j = 2;
    }

    {
        j = 3;
    }

    @Override
    protected int getValue() {
        return j;
    }

    @Override
    public void test(){
        System.out.println("调用了子类的方法");
    }

    public void zi(){
        System.out.println("子类特有方法");
    }
}

public class ConstructorExample {
    public static void main(String... args) {
        Bar bar = new Bar();
        // Foo bar = new Foo();
        /*bar.fu();
        bar.zi();*/
        DriverManager manager;
        System.out.println(bar.getValue());
    }
}