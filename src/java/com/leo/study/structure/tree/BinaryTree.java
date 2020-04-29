package com.leo.study.structure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date: 2020/3/26
 */
public class BinaryTree<E> {

    protected int size;
    protected Node<E> root;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    public void preorder(Vistor<E> vistor){
        preorder(root, vistor);
    }

    public void levelOrder(Vistor<E> vistor){
        if (root == null || vistor == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);
        while (!isEmpty()) {
            Node<E> node = queue.poll();
            vistor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    private void preorder(Node<E> node, Vistor<E> vistor){
        if (node == null || vistor == null) {
            return;
        }
        vistor.visit(node.element);
        preorder(node.left, vistor);
        preorder(node.right, vistor);
    }

    protected Node<E> predecessor(Node<E> root){
        if (root == null) {
            return root;
        }

        Node<E> node = root.left;
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    protected Node<E> successor(Node<E> root){
        if (root == null) {
            return root;
        }

        Node<E> node = root.right;
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    public int height () {
        if (root == null) {
            return 0;
        }

        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);
        int height = 0;
        int rowSize = 1;

        while (!isEmpty()) {
            Node<E> node = queue.poll();
            rowSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (rowSize == 0) {
                rowSize = queue.size();
                height++;
            }
        }
        return height;
    }

    public int height01(){
        return height(root);
    }

    private int height(Node<E> node){
        if (root == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public boolean isComplete(){
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null){
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {  //后面遍历的节点都必须是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(){
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node){
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 中序遍历
     */
    public void levelOrderTraversal(){
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public interface Vistor<E>{
        /**
         * 访问元素
         * @param element
         */
        boolean visit(E element);
    }

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }

        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }
}
