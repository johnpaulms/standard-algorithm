package com.training.array.sorting;

/**
 * Created by JohnPaul.Manohar on 9/23/2020.
 */
public class HeapSort {

    static int[] arr = new int[] {64,84,2,46,94,34,235,25,68,12,31,49,35,23,67,77};

    public static void main(String... args) {

        heapSort();

        System.out.println("");

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + ", ");
    }

    static void heapSort() {
        Heap heap = new Heap();

        for (int i = 0; i < arr.length; i++)
            heap.insert(arr[i]);

        for (int i = 0; i< arr.length;i++)
            arr[i] = heap.extractMin();
    }

    static class Heap {

        int[] arr = new int[1000];
        int size = 0;

        public Heap() {

        }

        void insert(int data) {
            arr[size] = data;
            trickleUp(size);
            size++;
        }

        int extractMin() {

            int top = arr[0];
            arr[0] = arr[--size];
            trickleDown(0);

            return top;
        }

        void trickleUp(int pos) {

            int bottom = arr[pos];
            int parent = (pos - 1) / 2;

            while (pos > 0 && arr[parent] > bottom) {
                arr[pos] = arr[parent];
                pos = parent;
                parent = (pos - 1) / 2;
            }

            arr[pos] = bottom;
        }

        void trickleDown(int pos) {
            int smallChild = pos;
            int top = arr[pos];

            while (pos < size / 2) {
                int leftChild = 2 * pos + 1;
                int rightChild = leftChild + 1;

                if(rightChild < size && arr[leftChild] < arr[rightChild]) smallChild = leftChild;
                else smallChild = rightChild;

                arr[pos] = arr[smallChild];
                pos = smallChild;

            }


            arr[pos] = top;
        }
    }
}
