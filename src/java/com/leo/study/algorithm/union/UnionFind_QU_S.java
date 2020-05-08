package com.leo.study.algorithm.union;

/**
 * QuickUnion  基于size优化
 */
public class UnionFind_QU_S extends UnionFind_QU {

    int[] sizes;

    public UnionFind_QU_S(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (sizes[p1] < sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}
