package com.training.tree.balance;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by johnpaul.manohar on 9/24/2020.
 */
public class AVLTree {

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

        insert(23);
        insert(24);
        insert(25);
        insert(26);
        insert(27);

        System.out.println("inorder: ");
        inorder(root);

        System.out.println("");
        System.out.println("preorder: ");
        preorder(root);

        System.out.println("");
        System.out.println("");
        levelorder(root);

    }

    static void insert(int data) {
        root = insertRec(root, data);
    }

    static Node insertRec(Node root, int data) {

        if(root == null) return new Node(data);

        if(root.data > data) root.left = insertRec(root.left, data);
        else root.right = insertRec(root.right, data);

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = balance(root);

        if(balance > 1 && root.left.data > data) {
            return rightRotate(root);
        }
        if(balance < -1 && root.right.data < data) {
            return leftRotate(root);
        }
        if(balance > 1 && root.left.data < data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance < -1 && root.right.data > data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static void delete(int data) {
        root = deleteRec(root, data);
    }

    static Node deleteRec(Node root, int data) {

        if(root == null) return root;

        if(root.data > data)
            root.left = deleteRec(root.left,data);
        else if(root.data < data)
            root.right = deleteRec(root.right, data);
        else {
            if(root.right == null) return root.left;
            else if(root.left == null) return root.right;
            else {
                int inorderSuccessor = inorderSuccessor(root.right);

                root.data = inorderSuccessor;
                root.right = deleteRec(root.right, inorderSuccessor);
            }
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = balance(root);

        if(balance > 1 && root.left.data > data) {
            return rightRotate(root);
        }
        if(balance < -1 && root.right.data < data) {
            return leftRotate(root);
        }
        if(balance > 1 && root.left.data < data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance < -1 && root.right.data > data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
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

    static Node leftRotate(Node root) {
        Node x = root.right;
        Node T2 = x.left;

        root.right = T2;
        x.left = root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node rightRotate(Node root) {
        Node x = root.left;
        Node T3 = x.right;


        root.left = T3;
        x.right = root;

        root.height = Math.max(height(x.left), height(root.right)) + 1;
        x.height = Math.max(height(x.left), height(root.right)) + 1;

        return x;
    }
    
    static int height(Node root) {

        if(root == null) return 0;
        else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if(leftHeight > rightHeight)
                return leftHeight + 1;
            else
                return rightHeight + 1;
        }
    }

    static int balance(Node root) {
        if(root == null) return 0;

        return height(root.left) - height(root.right);
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

    static void levelorder(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp != null) {
                System.out.print(temp.data + " ");
                if(temp.left != null ) queue.add(temp.left);
                if(temp.right != null ) queue.add(temp.right);

            }
            else {
                System.out.println("");
                if(!queue.isEmpty()) queue.add(null);
            }
        }

    }

    static class Node {
        int data;
        int height;
        Node left;
        Node right;

        public Node(int _data) {
            data = _data;
            left = right = null;
        }
    }
}
