package com.leo.study.pattern.proxy.staticproxy;

import com.leo.study.pattern.proxy.Person;

public class Father implements Person {

    @Override
    public int findLove() {
        return 0;
    }
}
