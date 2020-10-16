package com.training.array.search;

/**
 * Created by JohnPaul.Manohar on 9/20/2020.
 */
public class LinearSearch {

    public static void main(String... args) {

        int[] arr = new int[] {64,84,2,46,94,34,235,25,68,12,31,49,35,23,67,23,77,34};

        System.out.println(search(arr, 45));
        System.out.println(search(arr, 31));
        System.out.println(search(arr, 24));
        System.out.println(search(arr, 13));
        System.out.println(search(arr, 67));
        System.out.println(search(arr, 94));
    }

    //O(n)
    static boolean search(int[] arr, int key) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == key) return true;
        }

        return false;
    }
}
