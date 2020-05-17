package com.leo.study.pattern.singleton;

public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){}

    public static final LazyInnerClassSingleton getInstance(){
        return LazyHolder.LAZY;
    }

    private static class LazyHolder{
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}
