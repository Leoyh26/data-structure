package com.leo.study.algorithm.skipList;

import java.util.Comparator;

public class SkipList<K, V> {

    private static final int MAX_LEVEL = 32;

    private static final double P = 0.25;

    /**
     * 有效层数
     */
    private int level;

    private int size;

    private Comparator<K> comparator;

    private Node<K, V> first;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public SkipList() {
        this(null);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V put(K key, V value){
        keyCheck(key);
        Node<K, V> node = first;
        Node[] prevs = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                V oldV = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldV;
            }
            prevs[i] = node;
        }
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        for (int i = 0; i < newLevel; i++) {
            if (i < level) {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            } else {
                first.nexts[i] = newNode;
            }
        }
        size++;
        level = Math.max(level, newLevel);
        return null;
    }

    private int randomLevel(){
        int level = 1;
        if (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public V get(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }
        return null;
    }

    public V remove(K key){
        keyCheck(key);
        Node<K, V> node = first;
        Node[] prevs = new Node[level];
        boolean exist = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            prevs[i] = node;
            if (cmp == 0) {
                exist = true;
            }
        }
        if (!exist) {
            return null;
        }
        size--;
        Node<K, V> removedNode = node.nexts[0];
        for (int i = 0; i < removedNode.nexts.length; i++) {
            prevs[i].nexts[i] = removedNode.nexts[i];
        }

        // 更新跳表的层数
        int newLevel = level;
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }
        return removedNode.value;
    }

    private int compare(K k1, K k2){
        return comparator != null ? comparator.compare(k1, k2) : ((Comparable)k1).compareTo(k2);
    }

    private void keyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private static class Node<K, V>{
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
    }
}
