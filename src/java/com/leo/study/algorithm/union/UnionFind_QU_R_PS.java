package com.leo.study.algorithm.union;

/**
 * UnionFind_QU_R  路径分裂(Path Spliting)
 */
public class UnionFind_QU_R_PS extends UnionFind_QU_R {

    public UnionFind_QU_R_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}
