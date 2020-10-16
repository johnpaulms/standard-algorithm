package com.training.tree.algorithm;

import java.util.*;

/**
 * Created by JohnPaul.Manohar on 10/11/2020.
 */
public class SumOfDiameter {

    public static Node root;

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
        insert(40);
        insert(18);
        insert(73);
        delete(46);
        delete(77);

        diametersum(root);
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

    static void diametersum(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        HashMap<Integer, Integer> map = new HashMap<>();

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.left != null) {
                temp.left.level = temp.level + 1;
                queue.add(temp.left);
            }

            if(temp.right != null) {
                temp.right.level = temp.level;
                queue.add(temp.right);
            }

            if(!map.containsKey(temp.level)) map.put(temp.level, temp.data);
            else map.replace(temp.level, map.get(temp.level) + temp.data);
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }

    static class Node {
        int data;
        int level;
        Node left;
        Node right;

        public Node(int _data) {
            data = _data;
            level = 0;
            left = right = null;
        }
    }
}
