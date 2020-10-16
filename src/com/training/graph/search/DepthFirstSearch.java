package com.training.graph.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JohnPaul.Manohar on 10/6/2020.
 */
public class DepthFirstSearch {

    public static void main(String... args) {



    }

    static class Graph {

        LinkedList<Integer>[] edges;
        List<Integer> visited;
        int nVertex = 0;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for (int i = 0; i < nVertex; i++) {
                edges[i] = new LinkedList<>();
            }

            visited = new ArrayList<>(nVertex);
        }

        void addEdge(int start, int end) {
            edges[start].add(end);
            edges[end].add(start);
        }

        boolean search() {

            return false;
        }
    }
}