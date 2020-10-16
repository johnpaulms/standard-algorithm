package com.training.array.sorting;

/**
 * Created by JohnPaul.Manohar on 9/23/2020.
 */
public class InsertionSort {

    static int[] arr = new int[] {64,84,2,46,94,34,235,25,68,12,31,49,35,23,67,77};

    public static void main(String... args) {

        sort();

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + ", ");

    }

    //O(n^2)
    static void sort() {

        for (int i = 1; i < arr.length; i++) {

            int j = i;
            int key = arr[i];

            while (j > 0 && arr[j - 1] > key){
                arr[j] = arr[j - 1];
                j--;
            }

            arr[j] = key;
        }
    }
}
