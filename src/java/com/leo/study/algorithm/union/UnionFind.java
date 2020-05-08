package com.leo.study.algorithm.union;

public abstract class UnionFind {

    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("the capacity must be >= 0");
        }
        this.parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public abstract int find(int v);

    public abstract void union(int v1, int v2);

    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v1){
        if (v1 < 0 || v1 >= parents.length) {
            throw new IllegalArgumentException("the capatity must be >= 0");
        }
    }
}
