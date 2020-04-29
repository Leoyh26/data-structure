package com.leo.study.structure.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @date: 2020/4/26
 */
public class HashMap<K,V> implements Map<K,V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    private int size;
    private Node<K, V>[] table;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        table = new Node[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        resize();

        int index = index(key);
        // 取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }

        Node<K, V> parent = root;
        Node<K, V> node = root;
        Node<K, V> result = null;
        boolean searched = false;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // compare == 0 不能认为是同一个对象  需要走后面的扫描逻辑
            } else if (searched){
                // 已扫描过 不存在这个key  直接比较内存大小即可
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {
                // 先扫描  然后再根据内存地址大小决定左右
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    // 已经存在这个key
                    node = result;
                    cmp = 0;
                } else {
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0){
                node = node.left;
            } else {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                return oldValue;
            }
        } while (node != null);

        Node<K, V> newNode = createNode(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        fixAfterPut(newNode);
        size++;
        return null;
    }

    private void resize() {
        if (size / table.length <= DEFAULT_LOAD_FACTOR) {
            return;
        }
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];
        Queue<Node<K,V>> queue = new LinkedList<Node<K, V>>();
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) {
                continue;
            }

            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K,V> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                moveNode(node);
            }
        }

    }

    private void moveNode(Node<K, V> newNode) {
        // 重置
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;

        int index = index(newNode);
        // 取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            fixAfterPut(root);
            return;
        }

        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // compare == 0 不能认为是同一个对象  需要走后面的扫描逻辑
            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0){
                node = node.left;
            }
        } while (node != null);

        newNode.parent = parent;
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        fixAfterPut(newNode);
    }

    @Override
    public V get(K key) {
        Node<K,V> node = node(key);
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
        if (size == 0) {
            return false;
        }

        Queue<Node<K,V>> queue = new LinkedList<Node<K, V>>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K,V> node = queue.poll();
                if (Objects.equals(value, node.value)) {
                    return true;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) {
            return;
        }

        Queue<Node<K,V>> queue = new LinkedList<Node<K, V>>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K,V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) {
                    return;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    protected V remove(Node<K,V> node){
        if (node == null) {
            return null;
        }
        Node<K,V> willNode = node;

        size--;
        V oldValue = node.value;

        // 删除度为2的节点  用前驱或者后继节点替换该节点的值，并删除前驱或后继节点
        if (node.left != null && node.right != null) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            node = s;
        }

        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        // 删除度为1的节点
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            fixAfterRemove(replacement);
        } else if (node.parent == null) {
            table[index] = null;
        } else {  // 度为0 直接删除该节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            fixAfterRemove(node);
        }

        // 交给子类去处理
        afterRemove(willNode, node);

        return oldValue;
    }

    protected void afterRemove(Node<K, V> willNode, Node<K, V> node) {
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

    private Node<K, V> node(K key) {
        Node<K, V> node = table[index(key)];
        return node == null ? null : node(node, key);
    }

    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;

            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                /*if (cmp > 0) {
                    node = node.right;
                } else if (cmp < 0) {
                    node = node.left;
                } else {
                    return node;
                }*/
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null){
                return result;
            } else {
                // 只能往左边找
                node = node.left;
            }
            /*else if (node.left != null && (result = node(node.left, k1)) != null){
                return result;
            } else {
                return null;
            }*/
        }
        return null;
    }

    /**
     * 计算key的大小
     * @param k1
     * @param k2
     * @param h1  key1的hash值
     * @param h2  key2的hash值
     * @return
     */
    private int compare(K k1, K k2, int h1, int h2) {
        // 比较hash值
        int result = h1 - h2;
        if (result != 0) {
            return result;
        }

        // 比较 equals
        if (k1 == null ? k2 == null : k1.equals(k2)) {
            return 0;
        }

        // 比较类名
        if (k1 != null && k2 != null) {
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            result = k1Cls.compareTo(k2Cls);
            if (result != 0) {
                return result;
            }

            // 同一种类型并且具备可比较性
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }

        // 哈希值相等，同一种类型，但是不具备可比性
        // k1为null，k2不为null
        // k2为null，k1不为null

        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    private void fixAfterPut(Node<K, V> node){
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
            fixAfterPut(red(grand));
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

    private void fixAfterRemove(Node<K, V> node) {
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
                    fixAfterRemove(parent);
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
                    fixAfterRemove(parent);
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

    /**
     * 根据key生成对应的索引
     * @param key
     * @return
     */
    private int index (K key) {
        return hash(key) & (table.length - 1);
    }

    private int hash(K key){
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    private int index (Node<K,V> node) {
        return node.hash & (table.length - 1);
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
            table[index(node)] = child;
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
            table[index(node)] = child;
        }
        node.parent = child;
        if (node.left != null) {
            node.left.parent = node;
        }
    }

    public Node<K,V> createNode(K key, V value, Node<K, V> parent){
        return new Node<K, V>(key, value, parent);
    }

    protected static class Node<K, V>{
        int hash;
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node() {
            this(null, null, null);
        }

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
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
