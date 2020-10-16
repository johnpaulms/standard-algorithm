package com.training.array.sorting;

import java.util.Arrays;

/**
 * Created by JohnPaul.Manohar on 9/23/2020.
 */
public class RadixSort {

    static int[] arr = new int[] {64,84,2,46,94,34,235,25,68,12,31,49,35,23,67,77};

    public static void main(String... args) {

        radixSort(arr);

        System.out.println("");

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + ", ");

    }

    static void radixSort(int[] arr) {

        int max = getMax(arr);

        for (int x = 1; max / x > 1; x *= 10) {
            countSort(arr, x);
        }
    }

    static int getMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max) max = arr[i];
        }

        return max;
    }

    static void countSort(int[] arr, int x) {
        int[] arr2 = new int[arr.length];
        int i;
        int[] buckets = new int[10];
        Arrays.fill(buckets, 0);

        for (i = 0; i < arr.length; i++) {
            buckets[(arr[i] / x) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            buckets[i] += buckets[i-1];
        }

        for (i = arr.length - 1; i >= 0; i--) {
            arr2[buckets[(arr[i] / x) % 10] - 1] = arr[i];
            buckets[(arr[i] / x) % 10]--;
        }

        for (i = 0; i < arr.length; i++)
            arr[i] = arr2[i];
    }
}
