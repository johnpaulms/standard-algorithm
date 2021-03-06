package com.training.graph.shortestpath;

import java.util.LinkedList;

/**
 * https://algorithms.tutorialhorizon.com/djkstras-shortest-path-algorithm-adjacency-matrix-java-code/
 * 
 * Created by JohnPaul.Manohar on 10/6/2020.
 */
public class DijkstrasShortestPath {
    public static void main(String... args) {

        Graph g = new Graph(6);
        g.addEdge(0,1,4);
        g.addEdge(0,2,3);
        g.addEdge(1,2,1);
        g.addEdge(1,3,2);
        g.addEdge(2,3,4);
        g.addEdge(3,4,2);
        g.addEdge(4,5,6);
        g.findShortestPath(0);
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

        void findShortestPath(int source) {

            HeapNode[] heapNodes = new HeapNode[nVertex];
            boolean[] shortestPath = new boolean[nVertex];

            for (int i = 0; i < nVertex; i++) {
                heapNodes[i] = new HeapNode(i, Integer.MAX_VALUE);
            }

            heapNodes[source].distance = 0;

            MinHeap mh = new MinHeap();
            for (int i = 0; i < nVertex; i++) {
                mh.insert(heapNodes[i]);
            }

            while (!mh.isEmpty()) {
                HeapNode extractedNode = mh.extractmin();

                int extractedVertex = extractedNode.vertex;
                shortestPath[extractedVertex] = true;

                LinkedList<Edge> adjList = edges[extractedVertex];
                for(int i = 0; i < adjList.size(); i++) {
                    Edge e = adjList.get(i);
                    int destination = e.destination;

                    if(!shortestPath[destination]) {
                        int currentKey = heapNodes[destination].distance;
                        int newKey = heapNodes[extractedVertex].distance + e.weight;

                        if(currentKey > newKey) {
                            decreaseKey(mh, newKey, destination);
                            heapNodes[destination].distance = newKey;
                        }
                    }
                }
            }

            printShortestPath(heapNodes, source);
        }

        void decreaseKey(MinHeap mh, int newKey, int vertex) {
            int index = mh.indexes[vertex];
            mh.heapNodes[index].distance = newKey;
            mh.trickleup(index);
        }

        void printShortestPath(HeapNode[] heapNodes, int sourceVertex) {
            System.out.println("Dijsktra's Algorithm : ");

            for (int i = 0; i < nVertex; i++) {
                System.out.println("Source Vertex : " + sourceVertex + " to vertex " + i + " distance : " + heapNodes[i].distance);
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

    static class HeapNode {
        int vertex;
        int distance;

        public HeapNode(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
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

            while (pos > 0 && heapNodes[parent].distance > bottom.distance) {
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

                if(pos < size && heapNodes[leftChild].distance < heapNodes[rightChild].distance)
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

        boolean isEmpty() {return size == 0;}
    }
}
