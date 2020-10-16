package com.training.linkedlist.sorting;

/**
 * https://www.geeksforgeeks.org/merge-sort-for-linked-list/#:~:text=Merge%20sort%20is%20often%20preferred,be%20the%20pointer%20to%20head.
 * Created by JohnPaul.Manohar on 10/2/2020.
 */
public class MergeSort {

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
        insert(77);

        Node temp = head;
        System.out.println("");
        while(temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        head = mergeSort(head);

        temp = head;
        System.out.println("");
        while(temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
    }

    static void insert(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    static void delete(int data) {
        Node temp = head;

        if(head.data == data) {
            head = head.next;
        }
        else {
            while (temp != null) {
                if (temp.next.data == data) {
                    temp.next = temp.next.next;
                }
                temp = temp.next;
            }
        }
    }

    static Node mergeSort(Node head) {

        if(head == null || head.next == null) return head;

        Node middle = getMiddle(head);
        Node nextToMiddle = middle.next;

        middle.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(nextToMiddle);
        Node sortedList = mergeSorted(left, right);

        return sortedList;
    }

    static Node getMiddle(Node head) {

        if(head == null) return head;

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    static Node mergeSorted(Node left, Node right) {
        Node result = null;

        if (left == null) return right;
        if (right == null) return left;

        if(left.data <= right.data) {
            result = left;
            result.next = mergeSorted(left.next, right);
        }
        else {
            result = right;
            result.next = mergeSorted(left, right.next);
        }

        return result;
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
