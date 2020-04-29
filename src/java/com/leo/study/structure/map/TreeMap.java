package com.leo.study.structure.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @date: 2020/4/26
 */
public class TreeMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private int size;
    private Node<K, V> root;

    private Comparator<K> comparator;

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        if (root == null) {
            root = new Node<K,V>(key, value, null);
            size++;

            afterPut(root);
            return null;
        }

        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = compare(key, node.key);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0){
                node = node.left;
            } else {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        Node<K, V> newNode = createNode(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        afterPut(newNode);
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) {
            return false;
        }
        Queue<Node<K, V>> queue = new LinkedList<Node<K, V>>();
        queue.offer(root);
        while (!isEmpty()) {
            Node<K, V> node = queue.poll();
            if (valEquals(node.value, value)) {
                return true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        traversal(root, visitor);
    }

    private void traversal(Node<K, V> root, Visitor<K, V> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        traversal(root.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.visit(root.key, root.value);
        traversal(root.right, visitor);
    }

    private boolean valEquals(V v1, V v2){
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    private Node<K, V> node (K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = compare(node.key, key);
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

    private Node<K,V> createNode(K key, V value, Node<K,V> node){
        return new Node<K,V>(key, value, node);
    }

    private void afterPut(Node<K, V> node){
        Node<K, V> parent = node.parent;
        // 如果父节点是null，证明是根节点，直接染色黑色即可
        if (parent == null) {
            black(node);
        }
        // 如果父节点是黑色的  无需操作  直接返回
        if (isBlack(parent)) {
            return;
        }

        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = parent.parent;

        // 上溢情况：叔父节点是红色的
        // 将父节点和叔父节点染成黑色  并将祖父节点上移
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterPut(red(grand));
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

    private V remove(Node<K, V> node){
        if (node == null) {
            return null;
        }
        size--;
        V oldValue = node.value;

        // 删除度为2的节点  用前驱或者后继节点替换该节点的值，并删除前驱或后继节点
        if (node.left != null && node.right != null) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node = s;
        }

        Node<K, V> replacement = node.left != null ? node.left : node.right;
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
        } else {  // 度为0 直接删除该节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
        return oldValue;
    }

    private void afterRemove(Node<K, V> node) {
        // 如果删除的元素是红色 不需要有任何操作
        /*if (isRed(node)) {
            return;
        }*/

        // 用以取代node的子节点是红色，只需变为黑色即可
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点
        // 兄弟节点
        // 之前删除的代码里面将 父节点到该节点的指向已经清空 所以不能使用获取兄弟节点的方法
        boolean left = node.parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;

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
    
    private Node<K, V> successor(Node<K, V> root){
        if (root == null) {
            return root;
        }

        Node<K, V> node = root.right;
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
    
    private int compare(K e1, K e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<K>) e1).compareTo(e2);
    }

    private void keyNotNullCheck(K key){
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private Node<K, V> color (Node<K, V> node, boolean color) {
        if (node == null) {
            return node;
        }
        node.color = color;
        return node;
    }

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> child = node.right;
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

    private void rotateRight(Node<K, V> node) {
        Node<K, V> child = node.left;
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
    
    private static class Node<K, V>{
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
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

        public Node<K, V> sibling() {
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
