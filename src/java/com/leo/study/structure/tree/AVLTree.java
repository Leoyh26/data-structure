package com.leo.study.structure.tree;

import java.util.Comparator;

/**
 * @date: 2020/3/26
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                // LL: 右旋
                rotateRight(grand);
            } else {
                // LR: RR左旋 —> LL右旋
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {
                // RL: LL右旋 —> RR左旋
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                // RR: 左旋
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> node) {
        Node<E> child = node.right;
        node.right = child.left;
        child.left = node;

        child.parent = node.parent;
        if (node.isLeftChild()) {
            node.parent.left = child;
        } else if (node.isRightChild()) {
            node.parent.right = child;
        } else {
            root = child;
        }

        node.parent = child;
        if (node.right != null) {
            node.right.parent = node;
        }

        updateHeight(node);
        updateHeight(child);
    }

    private void rotateRight(Node<E> node) {
        Node<E> child = node.left;
        node.left = child.right;
        child.right = node;


        child.parent = node.parent;
        if (node.isLeftChild()) {
            node.parent.left = child;
        } else if (node.isRightChild()) {
            node.parent.right = child;
        } else {
            root = child;
        }
        node.parent = child;
        if (node.left != null) {
            node.left.parent = node;
        }

        updateHeight(node);
        updateHeight(child);
    }

    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E>{
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left : right;
        }
    }
}
