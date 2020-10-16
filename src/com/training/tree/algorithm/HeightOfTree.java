package com.training.tree.algorithm;

/**
 * Created by JohnPaul.Manohar on 9/27/2020.
 */
public class HeightOfTree {

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

        System.out.println("height : " + height(root));

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
            else if (root.right == null) return root.left;
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

    static int height(Node root) {

        if(root == null) return 0;
        else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if(leftHeight > rightHeight) return leftHeight + 1;
            else return rightHeight + 1;
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
