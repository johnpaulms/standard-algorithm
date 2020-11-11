package com.training.graph.sorting;

import java.util.LinkedList;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/topological-sorting/
 *
 * Created by JohnPaul.Manohar on 10/30/2020.
 */
public class TopologicalSorting {
    public static void main(String... args) {
        Graph g = new Graph(6);
        g.addEdge(5,2);
        g.addEdge(5,0);
        g.addEdge(4,0);
        g.addEdge(4,1);
        g.addEdge(2,3);
        g.addEdge(3,1);
        g.topologicalSort();
    }

    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for(int i = 0; i < nVertex; i++) edges[i] = new LinkedList<>();
        }

        void addEdge(int source, int destination) {
            Edge e = new Edge(source, destination);

            edges[source].add(e);
            //edges[destination].add(e);
        }

        void topologicalSort() {
            Stack<Integer> stack = new Stack<>();

            boolean[] visited = new boolean[nVertex];

            for (int i = 0; i < nVertex; i++) visited[i] = false;

            for (int i = 0; i < nVertex; i++) {
                if(!visited[i]) {
                    sortUtil(i, visited, stack);
                }
            }

            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + ", ");
            }
        }

        void sortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
            visited[vertex] = true;

            LinkedList<Edge> adjList = edges[vertex];

            for(int i = 0; i < adjList.size(); i++) {
                if(!visited[adjList.get(i).destination]) {
                    sortUtil(adjList.get(i).destination, visited, stack);
                }
            }

            stack.push(new Integer(vertex));
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
