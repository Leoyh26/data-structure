package com.leo.study.pattern.singleton;

public class LazySingleton {

    private static LazySingleton lazySingleton = null;

    private LazySingleton(){}

    public static synchronized LazySingleton getInstance(){
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
