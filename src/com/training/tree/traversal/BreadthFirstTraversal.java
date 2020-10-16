package com.training.tree.traversal;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by johnpaul.manohar on 9/24/2020.
 */
public class BreadthFirstTraversal {

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

        levelorder(root);


        delete(46);
        delete(77);


        levelorder(root);

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

        if(root.data > data)
            root.left = deleteRec(root.left, data);
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

        return root;
    }

    static int inorderSuccessor(Node root) {
        int successor = root.data;

        while (root != null ) {
            successor = root.data;
            root = root.left;
        }

        return successor;
    }

    static void levelorder(Node root) {

        Queue<Node> queue = new LinkedList<>();

        root.level = 0;
        queue.add(root);

        int currLevel = -1;

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(currLevel < temp.level) {
                System.out.println("");
                currLevel = temp.level;
            }
            System.out.print(temp.data + " ");

            if(temp.left != null) {
                temp.left.level = temp.level + 1;
                queue.add(temp.left);
            }

            if(temp.right != null) {
                temp.right.level = temp.level + 1;
                queue.add(temp.right);
            }

        }
    }

    static class Node {
        int data;
        Node left;
        Node right;
        int level;

        public Node(int _data) {
            data = _data;
            left = right = null;
            level = -1;
        }
    }
}
