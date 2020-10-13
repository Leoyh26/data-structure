package com.leo.study.test;

/**
 * @author: liuyanhui
 * @date: 2020/8/21
 */
class X{
    Y y=new Y();
    /*static {
        System.out.println("static X");
    }*/
    public X(){
        System.out.print("X");
    }
}
class Y{
    public Y(){
        System.out.print("Y");
    }
}
public class Z extends X{
    Y y=new Y();
    public Z(){
        System.out.print("Z");
    }
    public static void main(String[] args) {
        new Z();
    }
}
