package com.training.tree.balance;

/**
 * Created by johnpaul.manohar on 9/24/2020.
 */
public class SplayTree {

    static Node root;

    public static void main(String... args) {

        insert(1);
        insert(2);
        insert(3);
        insert(4);
        insert(5);
        insert(6);
        insert(7);
        insert(8);
        insert(9);
        insert(10);
        insert(11);
        insert(12);
        insert(13);
        insert(14);
        insert(15);
        insert(16);
        insert(17);
        insert(18);
        insert(19);
        insert(20);
        insert(21);
        insert(22);

        delete(15);

        System.out.println("inorder: ");
        inorder(root);

        System.out.println("");
        System.out.println("preorder: ");
        preorder(root);

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

        if(root.data > data) root.left = deleteRec(root.left, data);
        else if(root.data < data) root.right = deleteRec(root.right, data);
        else {

            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else {
                int inorderSuccessor = inorderSuccessor(root.right);

                root.data = inorderSuccessor;
                root.right = deleteRec(root.right, inorderSuccessor);
            }
        }

        return root;
    }

    static int inorderSuccessor(Node root) {
        int successor = root.data;

        while (root != null) {
            successor = root.data;
            root = root.left;
        }

        return successor;
    }

    static boolean search(int key) {
        Node temp = root;

        while (temp != null) {

            if(temp.data > key) temp = temp.left;
            else if(temp.data < key) temp = temp.right;
            else return true;
        }

        return false;
    }

    static void rebalance(Node root) {

    }

    static void inorder(Node root) {
        if(root != null) {
            inorder(root.left);
            System.out.print(root.data + " -> ");
            inorder(root.right);
        }
    }

    static void preorder(Node root) {
        if(root != null) {
            System.out.print(root.data + " -> ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int _data) {
            data = _data;
            left = right = null;
        }
    }
}
