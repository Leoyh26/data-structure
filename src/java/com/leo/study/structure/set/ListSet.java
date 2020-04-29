package com.leo.study.structure.set;

import com.leo.study.structure.list.MyList;

import java.util.LinkedList;
import java.util.List;

/**
 * @date: 2020/4/26
 */
public class ListSet<E> implements Set<E> {

    List<E> list = new LinkedList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if (index != MyList.ELEMENT_NOT_FIND) {
            list.set(index, element);
        } else {
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != MyList.ELEMENT_NOT_FIND) {
            list.remove(index);
        }
    }

    @Override
    public void traversal(Vistor<E> vistor) {
        if (vistor == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (vistor.visit(list.get(i))) {
                return;
            }
        }
    }
}
