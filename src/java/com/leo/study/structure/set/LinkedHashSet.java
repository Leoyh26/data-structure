package com.leo.study.structure.set;

import com.leo.study.structure.map.LinkedHashMap;
import com.leo.study.structure.map.Map;

/**
 * @date: 2020/4/26
 */
public class LinkedHashSet<E> implements Set<E> {

    private LinkedHashMap<E, Object> map = new LinkedHashMap<E, Object>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(final Vistor<E> vistor) {
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return vistor.visit(key);
            }
        });
    }

}
