package com.training.graph.search;

import java.util.LinkedList;

/**
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class AStarSearch {
    public static void main(String... args) {

    }

    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;

        public Graph(int vertex) {
            edges = new LinkedList[nVertex];
            nVertex = vertex;

            for(int i = 0 ; i < nVertex; i++) edges[i] = new LinkedList<Edge>();
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
