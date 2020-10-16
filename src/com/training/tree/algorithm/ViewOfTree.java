package com.training.tree.algorithm;

import java.util.*;

/**
 * Created by JohnPaul.Manohar on 10/7/2020.
 */
public class ViewOfTree {

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
        insert(40);
        insert(18);
        insert(73);
        //delete(46);
        //delete(77);

        levelorder(root);

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Left side view : ");
        leftview(root);

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Right side view : ");
        rightview(root);


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Top view : ");
        topview(root);


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Bottom view : ");
        bottomview(root);


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Boundary view : ");
        boundaryview(root);


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Zigzag view : ");
        spiralview(root);
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

        while (root != null) {
            successor = root.data;
            root = root.left;
        }

        return successor;
    }

    static void topview(Node root) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.left != null) {
                temp.left.level = temp.level - 1;
                queue.add(temp.left);
            }

            if(temp.right != null) {
                temp.right.level = temp.level + 1;
                queue.add(temp.right);
            }

            if(!map.containsKey(temp.level)) map.put(temp.level, temp.data);

        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.print(e.getValue() + ", ");
        }
    }

    static void bottomview(Node root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.left != null) {
                temp.left.level = temp.level - 1;
                queue.add(temp.left);
            }

            if(temp.right != null) {
                temp.right.level = temp.level + 1;
                queue.add(temp.right);
            }

            if(!map.containsKey(temp.level)) map.put(temp.level, temp.data);
            else map.replace(temp.level, temp.data);
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.print(e.getValue() + ", ");
        }
    }

    static void leftview(Node root) {

        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.left != null) {
                temp.left.level = temp.level + 1;
                queue.add(temp.left);
            }

            if(temp.right != null) {
                temp.right.level = temp.level + 1;
                queue.add(temp.right);
            }

            if(!map.containsKey(temp.level)) map.put(temp.level, temp.data);
        }

        for(Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.print(e.getValue() + ", ");
        }

    }

    static void rightview(Node root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.left != null) {
                temp.left.level = temp.level + 1;
                queue.add(temp.left);
            }

            if(temp.right != null) {
                temp.right.level = temp.level + 1;
                queue.add(temp.right);
            }

            if(!map.containsKey(temp.level)) map.put(temp.level, temp.data);
            else map.replace(temp.level, temp.data);
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.print(e.getValue() + ", ");
        }
    }

    static void spiralview(Node root) {

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (true) {


            while (!stack1.isEmpty()) {
                Node temp = stack1.pop();

                System.out.print(temp.data + ", ");

                if (temp.left != null) stack2.push(temp.left);
                if (temp.right != null) stack2.push(temp.right);
            }

            while (!stack2.isEmpty()) {
                Node temp = stack2.pop();

                System.out.print(temp.data + ", ");

                if(temp.right != null) stack1.push(temp.right);
                if(temp.left != null) stack1.push(temp.left);
            }

            if(stack1.isEmpty() && stack2.isEmpty()) break;
        }

    }

    static void boundaryview(Node root) {
        Node temp = root;

        List<Integer> set = new ArrayList<>();

        while (temp != null) {
            if(!set.contains(temp.data)) set.add(temp.data);

            if(temp.left != null) temp = temp.left;
            else if(temp.right != null) temp = temp.right;
            else break;
        }

        temp = root.right;

        while (temp != null) {
            //System.out.print(temp.data + ", ");
            if(!set.contains(temp.data)) set.add(temp.data);

            if(temp.right != null) temp = temp.right;
            else if(temp.left != null) temp = temp.left;
            else break;
        }

        temp = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(temp);

        while (!queue.isEmpty()) {
            temp = queue.poll();

            if(temp.left != null) queue.add(temp.left);
            if(temp.right != null) queue.add(temp.right);

            if(temp.left == null && temp.right == null){
                if(!set.contains(temp.data)) set.add(temp.data);
            }
        }

        for(Integer i : set)
            System.out.print(i + ", ");
    }

    static void levelorder(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            Node temp = queue.poll();



            if(temp.left != null){
                temp.left.level = temp.level + 1;
                queue.add(temp.left);
            }
            if(temp.right != null){
                temp.right.level = temp.level + 1;
                queue.add(temp.right);
            }

            if(currentLevel < temp.level) {
                System.out.println("");
                currentLevel = temp.level;
            }

            System.out.print(temp.data + " ");
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
