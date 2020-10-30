package com.training.graph.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by JohnPaul.Manohar on 10/6/2020.
 */
public class BreadthFirstSearch {
    public static void main(String... args) {

        Graph g = new Graph(9);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(3, 6);
        g.addEdge(4, 6);
        g.addEdge(4, 7);
        g.addEdge(5, 7);
        g.addEdge(6, 8);
        g.addEdge(7, 8);

        g.print();
        g.search(0);
    }

    static class Graph {

        LinkedList<Integer>[] edges;
        int[] visited;
        int nVertex;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for (int i = 0; i < nVertex; i++) {
                edges[i] = new LinkedList<>();
            }

            visited = new int[nVertex];
        }

        void addEdge(int start, int end) {
            edges[start].add(end);
            edges[end].add(start);
        }

        void print() {
            for (int i = 0;i < nVertex; i++) {
                System.out.print("Showing edges for " + i + ": ");

                for (int j = 0; j < edges[i].size(); j++) {
                    System.out.print(edges[i].get(j) + " -> ");
                }

                System.out.println("");
            }
        }

        boolean search(int key) {

            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            visited[0] = 1;

            System.out.println("");

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                System.out.print(vertex + " -> ");
                for (int i = 0; i < edges[vertex].size(); i++) {
                    if(visited[edges[vertex].get(i)] == 0) {
                        queue.add(edges[vertex].get(i));
                        visited[edges[vertex].get(i)] = 1;
                    }
                }

            }

            return false;
        }
    }
}
