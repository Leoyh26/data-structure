package com.leo.study.structure.tree;

import java.util.Comparator;
/**
 * @date: 2020/3/26
 */
public class RBTree<E> extends BinarySearchTree<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        // 如果父节点是null，证明是根节点，直接染色黑色即可
        if (parent == null) {
            black(node);
        }
        // 如果父节点是黑色的  无需操作  直接返回
        if (isBlack(parent)) {
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;

        // 上溢情况：叔父节点是红色的
        // 将父节点和叔父节点染成黑色  并将祖父节点上移
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterAdd(red(grand));
            return;
        }

        if (parent.isLeftChild()) {
            red(grand);
            if (node.isLeftChild()) {
                // LL
                black(parent);
            } else {
                // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            red(grand);
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            } else {
                // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 如果删除的元素是红色 不需要有任何操作
        /*if (isRed(node)) {
            return;
        }*/

        // 用以取代node的子节点是红色，只需变为黑色即可
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点
        // 兄弟节点
        // 之前删除的代码里面将 父节点到该节点的指向已经清空 所以不能使用获取兄弟节点的方法
        boolean left = node.parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        if (left) {
            // 兄弟节点是红色
            // 兄弟节点是红色 parent左旋  让兄弟节点的子节点成为node的兄弟节点
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟节点
                sibling = parent.right;
            }

            // 兄弟节点是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个是红色子节点  父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else {
                // 兄弟节点至少有一个红色子节点
                if (isBlack(sibling.right)) {
                    // 兄弟节点的左子节点是黑色  要先进行旋转
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(sibling));
                black(parent);
                black(sibling.right);
                rotateLeft(parent);
            }
        } else {
            // 兄弟节点是红色
            // 兄弟节点是红色 parent右旋  让兄弟节点的子节点成为node的兄弟节点
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟节点
                sibling = parent.left;
            }

            // 兄弟节点是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个是红色子节点  父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else {
                // 兄弟节点至少有一个红色子节点
                if (isBlack(sibling.left)) {
                    // 兄弟节点的左子节点是黑色  要先进行旋转
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(sibling));
                black(parent);
                black(sibling.left);
                rotateRight(parent);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> node) {
        return new RBNode<E>(element, node);
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
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private Node<E> color (Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>)node).color = color;
        return node;
    }

    private static class RBNode<E> extends Node<E>{
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
