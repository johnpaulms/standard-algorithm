package com.training.graph.minimumspanningtree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by JohnPaul.Manohar on 10/27/2020.
 */
public class MST {
    public static void main(String... args) {

        Graph g = new Graph(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(3,4);

        g.minimumSpaningTree();
    }


    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;
        boolean[] visited;
        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];
            visited = new boolean[nVertex];

            for (int i = 0; i < nVertex; i++) {
                edges[i] = new LinkedList<>();
                visited[i] = false;
            }
        }

        void addEdge(int source, int destination) {
            Edge e = new Edge(source, destination);

            edges[source].add(e);
            edges[destination].add(e);
        }

        void minimumSpaningTree() {
            Stack<Integer> stack = new Stack<>();

            visited[0] = true;
            stack.push(0);

            while (!stack.isEmpty()) {
                int currentVertex = stack.peek();
                int nextVertex = getAdjacentVertex(currentVertex);

                if(nextVertex == -1) stack.pop();
                else {
                    visited[nextVertex] = true;
                    stack.push(nextVertex);

                    System.out.println(currentVertex + " -> " + nextVertex);
                }
            }
        }

        int getAdjacentVertex(int currentVertex) {
            for(int j = 0; j < edges[currentVertex].size(); j++) {
                int nextVertex = edges[currentVertex].get(j).destination;

                if(!visited[nextVertex]) return nextVertex;
            }

            return -1;
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
