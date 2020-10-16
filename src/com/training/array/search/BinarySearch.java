package com.training.array.search;

/**
 * Created by JohnPaul.Manohar on 9/20/2020.
 */
public class BinarySearch {

    static int[] arr = new int[] {2, 12, 23, 25, 31, 34, 35, 46, 49, 64, 67, 68, 77, 84, 94, 235};

    public static void main(String... args) {

        System.out.println(search(0, arr.length - 1, 45));
        System.out.println(search(0, arr.length - 1, 31));
        System.out.println(search(0, arr.length - 1, 24));
        System.out.println(search(0, arr.length - 1, 13));
        System.out.println(search(0, arr.length - 1, 67));
        System.out.println(search(0, arr.length - 1, 94));

        System.out.println("");
        System.out.println("");
        System.out.println("Binary Search Recursive : ");
        System.out.println("");
        System.out.println("");


        System.out.println(searchRecursive(0, arr.length - 1, 45));
        System.out.println(searchRecursive(0, arr.length - 1, 31));
        System.out.println(searchRecursive(0, arr.length - 1, 24));
        System.out.println(searchRecursive(0, arr.length - 1, 13));
        System.out.println(searchRecursive(0, arr.length - 1, 67));
        System.out.println(searchRecursive(0, arr.length - 1, 94));
    }

    //O(log n)
    static boolean search(int low, int high, int key) {

        while(low <= high) {

            int mid = (low + high) / 2;

            if(arr[mid] > key) {
                high = mid - 1;
            }
            else if(arr[mid] < key) {
                low = mid + 1;
            }
            else return true;
        }

        return false;
    }

    //O(log n)
    static boolean searchRecursive(int low, int high, int key) {

        if(low <= high) {

            int mid = (low + high) / 2;

            if(arr[mid] == key) return true;
            else if(arr[mid] > key) return searchRecursive(low, mid - 1, key);
            else return searchRecursive(mid + 1, high, key);
        }

        return false;
    }
}
