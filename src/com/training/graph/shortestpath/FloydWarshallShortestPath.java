package com.training.graph.shortestpath;

import java.util.LinkedList;

/**
 *
 * https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
 *
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class FloydWarshallShortestPath {
    public static void main(String... args) {

        Graph g = new Graph(4);
        g.addEdge(0,1,5);
        g.addEdge(0,3,10);
        g.addEdge(1,2,3);
        g.addEdge(2,3,1);
        g.findShortestPath();
    }

    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for (int i = 0; i < nVertex; i++) edges[i] = new LinkedList<>();
        }

        void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);
            edges[source].add(e);
            //edges[destination].add(e);
        }

        void findShortestPath() {

        }

        void printShortestPath() {

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
