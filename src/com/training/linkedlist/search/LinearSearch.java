package com.training.linkedlist.search;

/**
 * Created by johnpaul.manohar on 9/24/2020.
 */
public class LinearSearch {

    static Node head;

    public static void main(String... args) {

        insert(64);
        insert(84);
        insert(2);
        insert(46);
        insert(94);
        insert(34);
        insert(235);
        insert(25);
        insert(68);
        insert(12);
        insert(31);
        insert(49);
        insert(35);
        insert(23);
        insert(67);

        System.out.println(search(45));
        System.out.println(search(31));
        System.out.println(search(24));
        System.out.println(search(13));
        System.out.println(search(67));
        System.out.println(search(94));
        System.out.println(search(64));

    }

    static void insert(int data) {

        Node temp = new Node(data);
        temp.next = head;
        head = temp;
    }

    static boolean search(int key) {

        Node temp = head;

        while (temp.next != null) {
            if(temp.data == key) return true;

            temp = temp.next;
        }

        if(temp.data == key) return true;

        return false;
    }

    static class Node {
        int data;
        Node next;

        public Node(int _data) {
            data = _data;
            next = null;
        }
    }
}
