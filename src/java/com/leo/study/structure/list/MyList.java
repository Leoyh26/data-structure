package com.leo.study.structure.list;

/**
 * @date: 2020/3/26
 */
public interface MyList<E> {

    int ELEMENT_NOT_FIND = -1;

    void  clear();

    int size ();

    boolean isEmpty();

    boolean contains(E element);

    void add(E element);

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(E element);
}
