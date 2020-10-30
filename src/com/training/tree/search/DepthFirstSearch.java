package com.training.tree.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by JohnPaul.Manohar on 10/5/2020.
 */
public class DepthFirstSearch {

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

        /*System.out.println(search('k'));
        System.out.println(search('a'));
        System.out.println(search('z'));
        System.out.println(search('d'));
        System.out.println(search('o'));
        System.out.println(search('j'));
        System.out.println(search('e'));*/

        search('g');


    }

    static boolean search(char key) {
        List<Node> visited = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        visited.add(root);
        System.out.println("");
        System.out.print(stack.peek().data + " -> ");
        while (!stack.isEmpty()) {
            Node temp = stack.peek();

            if(temp.left != null && !visited.contains(temp.left)) {
                stack.push(temp.left);
                visited.add(temp.left);
                System.out.print(stack.peek().data + " -> ");
            }
            else if(temp.right != null && !visited.contains(temp.right)) {
                stack.push(temp.right);
                visited.add(temp.right);
                System.out.print(stack.peek().data + " -> ");
            }
            else {
                temp = stack.pop();
            }

        }

        return false;
    }

    static class Node {
        char data;
        Node left;
        Node right;

        public Node(char _data) {
            data = _data;
            left = right = null;
        }
    }
}
