package com.training.array.sorting;

/**
 * Created by JohnPaul.Manohar on 9/23/2020.
 */
public class MergeSort {

    static int[] arr = new int[] {64,84,2,46,94,34,235,25,68,12,31,49,35,23,67,77};

    public static void main(String... args) {


        mergesort(arr, 0, arr.length - 1);

        System.out.println("");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }

    }

    static void mergesort(int[] arr, int low, int high) {
        if(low < high) {

            int mid = (low + high) / 2;

            mergesort(arr, low, mid);
            mergesort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    static void merge(int[] arr, int low, int mid, int high) {

        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i = 0; i < n1; i++)
            L[i] = arr[low + i];

        for(int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = low;

        while (i < n1 && j < n2) {
            if(L[i] < R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }

            k++;
        }

        while (i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2){
            arr[k] = R[j];
            k++;
            j++;
        }
    }
}
