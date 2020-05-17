package com.leo.study.structure.graph;

public class Main {

    public static void main(String[] args) {
        testBfs();
    }

    static void testBfs(){
        // Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
        // graph.bfs("A");
        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
        graph.bfs(5);
    }

    static void test(){
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);
        graph.bfs("V1");

        // graph.print();

        // 删除顶点1
        /*graph.removeVertex("V1");
        System.out.println();
        System.out.println("删除顶点 V1 ----------------------");
        graph.print();

        System.out.println();
        System.out.println("删除边 V2 -> V3 ------------------");
        graph.removeEdge("V2", "V3");
        graph.print();*/
    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        return getObjectDoubleDirectedGraph(data, graph);
    }

    static Graph<Object, Double> getObjectDoubleDirectedGraph(Object[][] data, Graph<Object, Double> graph) {
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        return getObjectDoubleGraph(data, graph);
    }

    static Graph<Object, Double> getObjectDoubleGraph(Object[][] data, Graph<Object, Double> graph) {
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
