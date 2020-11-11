package com.training.graph.shortestpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        List<Edge> allEdges = new ArrayList<>();

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for (int i = 0; i < nVertex; i++) edges[i] = new LinkedList<>();
        }

        void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);
            //edges[source].add(e);
            //edges[destination].add(e);
            allEdges.add(e);
        }

        void findShortestPath() {
            int[][] distance = new int[nVertex][nVertex];
            int INF = 99999;

            for (int i = 0; i < nVertex; i++) {
                for (int j = 0; j < nVertex; j++) {
                    if(i == j) distance[i][j] = 0;
                    else distance[i][j] = INF;
                }
            }

            for (int i = 0; i < allEdges.size(); i++) {
                Edge e = allEdges.get(i);
                distance[e.source][e.destination] = e.weight;
            }

            for (int k = 0; k < nVertex; k++) {
                for (int i = 0; i < nVertex; i++) {
                    for (int j = 0; j < nVertex; j++) {
                        if(distance[i][k] + distance[k][j] < distance[i][j])
                            distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }

            printShortestPath(distance);
        }

        void printShortestPath(int[][] distance) {

            for (int i = 0; i < nVertex; i++) {
                for (int j = 0; j < nVertex; j++) {
                    if(distance[i][j] == 99999) System.out.print("INF  ");
                    else System.out.print(distance[i][j] + "    ");
                }
                System.out.println("");
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
