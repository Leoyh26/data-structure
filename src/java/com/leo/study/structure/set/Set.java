package com.leo.study.structure.set;

/**
 * @date: 2020/4/26
 */
public interface Set<E> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Vistor<E> vistor);

    public static abstract class Vistor<E>{
        boolean stop;
        abstract boolean visit(E element);
    }
}
