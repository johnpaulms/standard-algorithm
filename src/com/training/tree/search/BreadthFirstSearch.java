package com.training.tree.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by JohnPaul.Manohar on 10/4/2020.
 */
public class BreadthFirstSearch {

    static Node root;

    public static void main(String... args) {

        root = new Node('a');
        root.left = new Node('b');
        root.right = new Node('c');

        root.left.left = new Node('d');
        root.left.right = new Node('e');

        root.right.left = new Node('f');
        root.right.right = new Node('g');

        root.left.left.left = new Node('h');

        root.left.right.left = new Node('i');
        root.left.right.right = new Node('j');

        root.right.right.right = new Node('k');

        System.out.println(search('k'));
        System.out.println(search('a'));
        System.out.println(search('z'));
        System.out.println(search('d'));
        System.out.println(search('o'));
        System.out.println(search('j'));
        System.out.println(search('e'));


    }

    static boolean search(char key) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.data == key) return true;

            if(temp.left != null) queue.add(temp.left);
            if(temp.right != null) queue.add(temp.right);
        }

        return false;
    }

    static class Node {
        char data;
        Node left;
        Node right;

        public Node(char _data) {
            data = _data;
        }
    }
}
