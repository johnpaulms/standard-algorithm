package com.training.graph.shortestpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
 *
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class BellmanFordShortestPath {

    public static void main(String... args) {

        Graph g = new Graph(5);
        g.addEdge(0,1,-1);
        g.addEdge(0,2,4);
        g.addEdge(1,2,3);
        g.addEdge(1,3,2);
        g.addEdge(1,4,2);
        g.addEdge(3,2,5);
        g.addEdge(3,1,1);
        g.addEdge(4,3,-3);
        g.findShortestPath(0);
    }

    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;
        List<Edge> allEdges;
        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];
            allEdges = new ArrayList<>();

            for (int i = 0; i < nVertex; i++) edges[i] = new LinkedList<>();
        }

        void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);
            edges[source].add(e);
            allEdges.add(e);
        }

        void findShortestPath(int sourceVertex){
            int[] distance = new int[nVertex];

            for (int i = 0; i < nVertex; i++) {
                distance[i] = Integer.MAX_VALUE;
            }

            distance[sourceVertex] = 0;

            for (int i = 1; i < nVertex; i++) {
                for (int j = 0; j < allEdges.size(); j++) {
                    int source = allEdges.get(j).source;
                    int destination = allEdges.get(j).destination;
                    int weight = allEdges.get(j).weight;

                    if(distance[source] != Integer.MAX_VALUE && distance[source] + weight < distance[destination]) {
                        distance[destination] = distance[source] + weight;
                    }
                }
            }

            for (int i = 0; i < allEdges.size(); i++) {
                int source = allEdges.get(i).source;
                int destination = allEdges.get(i).destination;
                int weight = allEdges.get(i).weight;

                if(distance[source] != Integer.MAX_VALUE && distance[source] + weight < distance[destination]) {
                    System.out.println("Graph contains cycle");
                    return;
                }
            }

            printShortestPath(distance);
        }

        void printShortestPath(int[] distance) {
            for (int i = 0 ; i < distance.length; i++) {
                System.out.println("Source: " + i + " distance: " + distance[i]);
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
