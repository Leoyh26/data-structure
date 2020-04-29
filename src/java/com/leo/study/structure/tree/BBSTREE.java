package com.leo.study.structure.tree;

import java.util.Comparator;

/**
 * @date: 2020/3/26
 */
public class BBSTREE<E> extends BinarySearchTree<E> {

    public BBSTREE() {
        this(null);
    }

    public BBSTREE(Comparator<E> comparator) {
        super(comparator);
    }
}
