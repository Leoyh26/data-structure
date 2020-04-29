package com.leo.study.structure.tree;

import java.util.Comparator;

/**
 * @date: 2020/3/26
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element){
        elementNotNullCheck(element);
        if (root == null) {
            root = createNode(element, null);
            afterAdd(root);
            size++;
            return;
        }

        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0){
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        afterAdd(newNode);
        size++;
    }

    protected Node<E> createNode(E element, Node<E> node){
        return new Node<E>(element, node);
    }

    protected void afterAdd(Node<E> node){

    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private Node<E> node (E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(node.element, element);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    /**
     *
     * @param node 被删除的节点 或 用来取代被删除节点（度为1）的子节点
     */
    protected void afterRemove(Node<E> node){
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;

        // 删除度为2的节点  用前驱或者后继节点替换该节点的值，并删除前驱或后继节点
        if (node.left != null && node.right != null) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        Node<E> replacement = node.left != null ? node.left : node.right;
        // 删除度为1的节点
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            afterRemove(replacement);
        } else if (node.parent == null) {
            root = null;
            afterRemove(node);
        } else {  // 度为0 直接删除该节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element){
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
