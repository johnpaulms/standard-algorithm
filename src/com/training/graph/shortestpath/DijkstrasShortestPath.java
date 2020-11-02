package com.training.graph.shortestpath;

import java.util.LinkedList;

/**
 * https://algorithms.tutorialhorizon.com/djkstras-shortest-path-algorithm-adjacency-matrix-java-code/
 * 
 * Created by JohnPaul.Manohar on 10/6/2020.
 */
public class DijkstrasShortestPath {
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

        void addEdge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);
            edges[source].add(e);

            e = new Edge(destination, source, weight);
            edges[destination].add(e);
        }

        void findShortestPath() {

        }

        void decreaseKey(MinHeap mh, int newKey, int vertex) {

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

    static class HeapNode {
        int vertex;
        int key;

        public HeapNode(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }
    }

    static class MinHeap {
        HeapNode[] heapNodes = new HeapNode[1000];
        int[] indexes = new int[1000];
        int size = 0;


        void insert(HeapNode node) {
            heapNodes[size] = node;
            indexes[node.vertex] = size;
            trickleup(size++);
        }

        HeapNode getmin() {
            return heapNodes[0];
        }

        HeapNode extractmin() {
            HeapNode node = heapNodes[0];
            heapNodes[0] = heapNodes[--size];
            indexes[heapNodes[0].vertex] = 0;
            trickledown(0);
            return node;
        }

        void trickleup(int pos) {
            HeapNode bottom = heapNodes[pos];
            int parent = (pos - 1) / 2;

            while (pos > 0 && heapNodes[parent].key > bottom.key) {
                heapNodes[pos] = heapNodes[parent];
                indexes[heapNodes[pos].vertex] = pos;
                pos = parent;
                parent = (pos - 1) / 2;
            }

            heapNodes[pos] = bottom;
            indexes[heapNodes[pos].vertex] = pos;
        }

        void trickledown(int pos) {
            HeapNode top = heapNodes[pos];
            int smallerChild = pos;

            while (pos < size / 2) {
                int leftChild = 2 * pos + 1;
                int rightChild = leftChild + 1;

                if(pos < size && heapNodes[leftChild].key < heapNodes[rightChild].key)
                    smallerChild = leftChild;
                else
                    smallerChild = rightChild;

                heapNodes[pos] = heapNodes[smallerChild];
                indexes[heapNodes[pos].vertex] = pos;
                pos = smallerChild;
            }

            heapNodes[pos] = top;
            indexes[heapNodes[pos].vertex] = pos;
        }
    }
}
