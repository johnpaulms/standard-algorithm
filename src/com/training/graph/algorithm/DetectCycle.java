package com.training.graph.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://algorithms.tutorialhorizon.com/disjoint-set-data-structure-union-find-algorithm/
 * https://algorithms.tutorialhorizon.com/graph-find-cycle-in-undirected-graph-using-disjoint-set-union-find/
 *
 * Created by JohnPaul.Manohar on 10/27/2020.
 */
public class DetectCycle {

    public static void main(String... args) {

        Graph g = new Graph(6);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(3,4);
        g.addEdge(2,3);
        g.addEdge(4,5);

        System.out.println("Graph contains cycle : " + g.isCycle());
    }

    static class Graph {

        List<Edge> allEdges;
        LinkedList<Edge>[] edges;
        int nVertex;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];
            allEdges = new ArrayList<>();

            for (int i = 0; i < nVertex; i++) {
                edges[i] = new LinkedList<>();
            }
        }

        void addEdge(int source, int destination) {
            Edge e = new Edge(source, destination);
            edges[source].add(e);
            allEdges.add(e);
        }

        void makeSet(int[] parent) {

            for(int i = 0; i < parent.length; i++) parent[i] = i;
        }

        int find(int[] parent, int vertex) {

            if(parent[vertex] != vertex)
                return find(parent, parent[vertex]);

            return vertex;
        }

        void union(int[] parent, int x, int y) {

            int x_parent = find(parent, x);
            int y_parent = find(parent, y);

            parent[y_parent] = x_parent;
        }

        boolean isCycle() {

            int[] parent = new int[nVertex];

            makeSet(parent);

            for (int i = 0; i < allEdges.size(); i++) {
                Edge e = allEdges.get(i);

                int x_set = find(parent, e.source);
                int y_set = find(parent, e.destination);

                if(x_set == y_set) return true;
                else union(parent, x_set, y_set);

            }

            return false;
        }
    }

    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }
}
