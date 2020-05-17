package com.leo.study.structure.graph;

public interface Graph<V, E> {

    int edgesSize();

    int verticesSize();

    void addVertex(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeVertex(V v);

    void removeEdge(V from, V to);

    /**
     * breadth first search  广度优先搜索
     * @param begin
     */
    void bfs(V begin);
}
