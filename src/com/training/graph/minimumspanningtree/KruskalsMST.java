package com.training.graph.minimumspanningtree;

import java.util.*;

/**
 * https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
 *
 * Created by JohnPaul.Manohar on 10/7/2020.
 */
public class KruskalsMST {

    public static void main(String... args) {

        Graph g = new Graph(6);
        g.addEdge(0,1,4);
        g.addEdge(0,2,3);
        g.addEdge(1,2,1);
        g.addEdge(1,3,2);
        g.addEdge(2,3,4);
        g.addEdge(3,4,2);
        g.addEdge(4,5,6);
        g.kruskalMST();
    }

    static class Graph {
        LinkedList<Edge>[] edges;
        List<Edge> allEdges;
        int nVertex;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];
            allEdges = new ArrayList<>();
            for (int i = 0; i < nVertex; i++) edges[i] = new LinkedList<>();

        }

        void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);

            edges[source].add(e);
            edges[destination].add(e);
            allEdges.add(e);
        }

        void makeSet(int[] parent) {
            for(int i = 0; i < parent.length; i++) parent[i] = i;
        }

        int find(int[] parent, int vertex) {
            if(parent[vertex] != vertex) return find(parent, parent[vertex]);
            return vertex;
        }

        void union(int[] parent, int x, int y) {
            int x_parent = find(parent, x);
            int y_parent = find(parent, y);

            parent[y_parent] = x_parent;
        }

        void kruskalMST() {
            int[] parent = new int[nVertex];

            makeSet(parent);

            PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

            for (int i = 0; i < allEdges.size(); i++) pq.add(allEdges.get(i));

            List<Edge> mst = new ArrayList<>();

            int count = 0;

             while (count < nVertex - 1) {
                 Edge e = pq.remove();

                 int x_set = find(parent, e.source);
                 int y_set = find(parent, e.destination);

                 if(x_set != y_set) {
                     mst.add(e);
                     count++;
                     union(parent, x_set, y_set);
                 }
             }
            printSet(mst);
        }

        void printSet(List<Edge> edges) {

            for (int i = 0; i < edges.size(); i++) {
                Edge e = edges.get(i);

                System.out.println("Edge - " + i + " source : " + e.source + " destination : " + e.destination + " weight : " + e.weight);
            }

        }
    }

    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}
