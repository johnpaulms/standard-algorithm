package com.training.array.sorting;

/**
 * Created by JohnPaul.Manohar on 9/23/2020.
 */
public class QuickSort {

    static int[] arr = new int[] {64,84,2,46,94,34,235,25,68,12,31,49,35,23,67,77};

    public static void main(String... args) {

        quicksort(arr, 0, arr.length - 1);

        System.out.println("");

        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + ", ");

    }

    static void  quicksort(int[] arr, int low, int high) {

        if(low < high) {
            int pivot = partition(arr, low, high);

            quicksort(arr, low, pivot - 1);
            quicksort(arr, pivot + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = (low - 1);

        for(int j = low; j < high; j++) {
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return (i + 1);
    }
}
