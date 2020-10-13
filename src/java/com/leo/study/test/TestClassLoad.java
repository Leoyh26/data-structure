package com.leo.study.test;

import java.net.URL;
import java.util.List;

/**
 * @author: liuyanhui
 * @date: 2020/8/20
 */
public class TestClassLoad {

    /**
     * 创建一个类A
     * 该类中有一个方法draw，以及一个构造方法A
     */
    static class A{
        private static int AValue = 10;

        void draw(){
            System.out.println("A.draw()");
        }
        A(){
            System.out.println("A() before draw()");
            System.out.println("A.value = " + AValue);
            draw();
            System.out.println("A() after draw()");
        }

        private int ATest = 11;
    }

    /**
     * 创建一个类B，继承A
     * 该类中同样有一个方法draw，以及一个构造方法B
     */
    static class B extends A {
        private int value = 1;
        private static int sValue = 300;
        private static boolean flag;

        @Override
        void draw(){
            System.out.println("B.draw(),value="+value);
        }
        B(int v){
            value=v;
            System.out.println("B.B(),value="+value);
        }

        private int Btest = 9;
    }

    /*现在我们调用B的构造函数，构造一个B*/
    public static void main(String[] args) {
        /*new B(5);*/
        //获取系统/应用类加载器
        /*ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统/应用类加载器：" + appClassLoader);
        //获取系统/应用类加载器的父类加载器，得到扩展类加载器
        ClassLoader extcClassLoader = appClassLoader.getParent();
        System.out.println("扩展类加载器" + extcClassLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
        //获取扩展类加载器的父加载器，但因根类加载器并不是用Java实现的所以不能获取
        System.out.println("扩展类的父类加载器：" + extcClassLoader.getParent());*/

        ClassLoader classLoader;

        //输出ClassLoaderText的类加载器名称
        System.out.println("TestClassLoad类的加载器的名称: " + TestClassLoad.class.getClassLoader().getClass().getName());
        System.out.println("System类的加载器的名称: " + System.class.getClassLoader());
        System.out.println("List类的加载器的名称: " + List.class.getClassLoader());

        ClassLoader cl = TestClassLoad.class.getClassLoader();
        while(cl != null){
            System.out.print(cl.getClass().getName()+"->");
            cl = cl.getParent();
        }
        System.out.println(cl);


        /*URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }*/

    }
}
