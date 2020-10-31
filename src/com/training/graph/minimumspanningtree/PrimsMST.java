package com.training.graph.minimumspanningtree;

import java.util.LinkedList;
import java.util.List;

/**
 * https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-list-and-min-heap/
 *
 * Created by JohnPaul.Manohar on 10/6/2020.
 */
public class PrimsMST {

    public static void main(String... args) {

        Graph g = new Graph(6);
        g.addEge(0,1,4);
        g.addEge(0,2,3);
        g.addEge(1,2,1);
        g.addEge(1,3,2);
        g.addEge(2,3,4);
        g.addEge(3,4,2);
        g.addEge(4,5,6);
        g.primMST();
    }

    static class Graph {
        LinkedList<Edge>[] edges;
        int nVertex;

        public Graph(int vertex) {
            nVertex = vertex;
            edges = new LinkedList[nVertex];

            for (int i = 0; i < nVertex; i++) {
                edges[i] = new LinkedList<>();
            }
        }

        void addEge(int source, int destination, int weight) {
            Edge e = new Edge(source, destination, weight);

            edges[source].add(e);
            edges[destination].add(e);
        }

        void primMST() {
            int[] key = new int[nVertex];
            boolean[] inHeap = new boolean[nVertex];
            HeapNode[] heapNodes = new HeapNode[nVertex];
            ResultSet[] resultSets = new ResultSet[nVertex];

            for (int i = 0; i < nVertex; i++) {
                heapNodes[i] = new HeapNode(i, Integer.MAX_VALUE);
                resultSets[i] = new ResultSet();
                resultSets[i].parent = -1;
                inHeap[i] = true;
                key[i] = Integer.MAX_VALUE;
            }

            heapNodes[0].key = 0;
            MinHeap mh = new MinHeap();

            for (int i = 0; i < nVertex; i++) mh.insert(heapNodes[i]);

            while (!mh.isEmpty()) {
                HeapNode currentNode = mh.extractMin();
                int currentVertex = currentNode.vertex;
                inHeap[currentVertex] = false;

                LinkedList<Edge> adjList = edges[currentVertex];

                for(int i = 0; i < adjList.size(); i++) {
                    Edge e = adjList.get(i);

                    if(inHeap[e.destination]) {
                        int destination = e.destination;
                        int newKey = e.weight;

                        if(key[destination] > newKey) {
                            decreaseKey(mh, newKey, destination);
                            resultSets[destination].parent = currentVertex;
                            resultSets[destination].weight = newKey;
                            key[destination] = newKey;
                        }
                    }
                }
            }

            printSet(resultSets);
        }

        void decreaseKey(MinHeap mh, int newKey, int pos) {
            int index = mh.indexes[pos];
            mh.heapNodes[index].key = newKey;
            mh.trickleUp(index);
        }

        void printSet(ResultSet[] resultSets) {
            int totalWeight = 0;
            System.out.println("Minimum spanning tree : ");
            for (int i = 0; i < resultSets.length; i++) {
                System.out.println("Edge : " + i + " - " + resultSets[i].parent + " weight : " + resultSets[i].weight);
                totalWeight += resultSets[i].weight;
            }
            System.out.println("Total Weight : " + totalWeight);
        }
    }

    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination,int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class ResultSet {
        int parent;
        int weight;
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

        public MinHeap() {
        }

        void insert(HeapNode node) {
            heapNodes[size] = node;
            indexes[node.vertex] = size;
            trickleUp(size++);
        }

        HeapNode getMin() {
            return heapNodes[0];
        }

        HeapNode extractMin() {
            HeapNode node = heapNodes[0];
            heapNodes[0] = heapNodes[--size];
            indexes[heapNodes[0].vertex] = 0;
            trickleDown(0);
            return node;
        }

        boolean isEmpty() {
            return size > 0 ? false : true;
        }

        void trickleUp(int pos) {

            HeapNode bottom = heapNodes[pos];
            int parent = (pos - 1) / 2;
            while (pos > 0 && heapNodes[parent].key > bottom.key) {
                heapNodes[pos] = heapNodes[parent];
                indexes[heapNodes[parent].vertex] = pos;
                pos = parent;
                parent = (pos - 1) / 2;
            }

            heapNodes[pos] = bottom;
            indexes[heapNodes[pos].vertex] = pos;
        }

        void trickleDown(int pos) {
            HeapNode top = heapNodes[pos];
            int smallerChild;

            while (pos < (size / 2)) {

                int leftChild = 2 * pos + 1;
                int rightChild = leftChild + 1;

                if(rightChild < size && heapNodes[leftChild].key < heapNodes[rightChild].key) {
                    smallerChild = leftChild;
                }
                else {
                    smallerChild = rightChild;
                }
                heapNodes[pos] = heapNodes[smallerChild];
                indexes[heapNodes[smallerChild].vertex] = pos;
                pos = smallerChild;
            }

            heapNodes[pos] = top;
            indexes[heapNodes[pos].vertex] = pos;
        }
    }
}
