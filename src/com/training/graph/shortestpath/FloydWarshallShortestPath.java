package com.training.graph.shortestpath;

import java.util.LinkedList;

/**
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class FloydWarshallShortestPath {
    public static void main(String... args) {

    }

    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for (int i = 0; i < nVertex; i++) edges[i] = new LinkedList<>();
        }

        void addEdge(int source, int destination) {
            Edge e = new Edge(source, destination);
            edges[source].add(e);
            edges[destination].add(e);
        }
    }

    static class Edge {
        int source;
        int destination;

        public Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }
}
