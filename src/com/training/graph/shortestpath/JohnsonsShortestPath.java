package com.training.graph.shortestpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * https://www.geeksforgeeks.org/johnsons-algorithm-for-all-pairs-shortest-paths-implementation/?ref=lbp
 *
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class JohnsonsShortestPath {
    public static void main(String... args) {

        Graph g = new Graph(4);
        g.addEdge(0,1,-5);
        g.addEdge(0,2,2);
        g.addEdge(0,3,3);
        g.addEdge(1,2,4);
        g.addEdge(2,3,1);
        g.findShortestPath(0);
    }

    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;
        List<Edge> allEdges = new ArrayList<>();

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for (int i = 0; i < nVertex; i++) edges[i] = new LinkedList<Edge>();
        }

        void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);
            //edges[source].add(e);
            //edges[destination].add(e);
            allEdges.add(e);
        }

        int[] bmfShortestPath(int sourceVertex) {
            //int[][] sp = new int[nVertex][nVertex];
            int[] distance = new int[nVertex];

            for (int i = 0; i < nVertex; i++) {
                distance[i] = Integer.MAX_VALUE;
            }

            distance[sourceVertex] = 0;

            for (int i = 1; i < nVertex; i++) {
                for (int j = 0; j < allEdges.size(); j++) {
                    Edge e = allEdges.get(j);

                    int source = e.source;
                    int destination = e.destination;
                    int weight = e.weight;

                    if(distance[source] != Integer.MAX_VALUE && distance[source] + weight < distance[destination])
                        distance[destination] = distance[source] + weight;
                }
            }

            return distance;
        }

        void dikstraShortestPath(int sourceVertex) {

        }

        void findShortestPath(int sourceVertex) {

            int[] modifiedWeight = bmfShortestPath(sourceVertex);

            //int[][]
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
