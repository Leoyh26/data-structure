package com.leo.study.structure.set;

import com.leo.study.structure.tree.BinaryTree;
import com.leo.study.structure.tree.RBTree;

import java.util.Comparator;

/**
 * @date: 2020/4/26
 */
public class TreeSet<E> implements Set<E> {

    private RBTree<E> tree;

    public TreeSet() {
        this(null);
    }

    public TreeSet(Comparator<E> comparator) {
        tree = new RBTree<E>(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(final Vistor<E> vistor) {
        tree.levelOrder(new BinaryTree.Vistor<E>() {
            @Override
            public boolean visit(E element) {
                return vistor.visit(element);
            }
        });
    }
}
