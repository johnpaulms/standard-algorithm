package com.training.tree.search;

/**
 * Created by johnpaul.manohar on 9/25/2020.
 */
public class BinarySearch {

    static Node root;

    public static void main(String... args) {

        insert(49);
        insert(46);
        insert(25);
        insert(34);
        insert(31);
        insert(23);
        insert(12);
        insert(2);
        insert(35);
        insert(68);
        insert(67);
        insert(235);
        insert(94);
        insert(77);
        insert(84);
        insert(64);


        System.out.println(search(45));
        System.out.println(search(31));
        System.out.println(search(24));
        System.out.println(search(13));
        System.out.println(search(67));
        System.out.println(search(94));

        System.out.println("");
        System.out.println("");
        System.out.println("Binary Search Recursive : ");
        System.out.println("");
        System.out.println("");

        delete(31);

        System.out.println(searchRecursive(root,45));
        System.out.println(searchRecursive(root,31));
        System.out.println(searchRecursive(root,24));
        System.out.println(searchRecursive(root,13));
        System.out.println(searchRecursive(root,67));
        System.out.println(searchRecursive(root,94));

    }

    static void insert(int data) {
        root = insertRec(root, data);
    }

    static Node insertRec(Node root, int data) {

        if(root == null) return new Node(data);

        if(root.data > data) root.left = insertRec(root.left, data);
        else root.right = insertRec(root.right, data);

        return root;
    }

    static void delete(int data) {
        root = deleteRec(root, data);
    }

    static Node deleteRec(Node root, int data) {

        if(root == null) return root;

        if(root.data > data) {
            root.left = deleteRec(root.left, data);
        }
        else if(root.data < data) {
            root.right = deleteRec(root.right, data);
        }
        else {
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else {
                int inorderSucessor = findSuccessor(root.right);
                root.data = inorderSucessor;
                root.right = deleteRec(root.right, inorderSucessor);
            }
        }

        return root;
    }

    static int findSuccessor(Node root) {
        int successor = root.data;

        while (root != null) {
            successor = root.data;
            root = root.left;
        }

        return successor;
    }

    static boolean search(int key) {

        Node curr = root;

        while (curr != null) {
            if(curr.data == key) return true;
            else if(curr.data > key) curr = curr.left;
            else curr = curr.right;
        }

        return false;
    }

    static boolean searchRecursive(Node root, int key) {

        if(root != null) {

            if(root.data == key) return true;
            else if(root.data > key) return searchRecursive(root.left, key);
            else return searchRecursive(root.right, key);
        }

        return false;
    }

    static class Node {
        int data;
        public Node left;
        public Node right;

        public Node(int _data) {
            data = _data;
            left = right = null;
        }
    }
}
