package com.example.DSA.Linkedlist;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(4);
        list.insert(5);
        list.insert(6);
        list.insert(7);
        list.insert(8);
        list.swap(2, 4);
        list.print();
    }

    public class Node {
        private int val;
        private Node next;

        public Node(int data) {
            this.val = data;
        }
    }

    public Node head;

    public void insert(int node) {
        Node newNode = new Node(node);
        if(head == null) {
            head = newNode;
        }
        else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
    public void swap(int left, int right) {
        Node prev = null;
        Node curr = head;
        Node leftNode = null;
        Node rightNode = null;
        while (left > 1) {
            prev = curr;
            curr = curr.next;
            left--;
            right--;
        }
        Node connection = prev;
        Node tail = curr;
        while(right > 0) {
            Node next_node = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next_node;
            right--;
        }
        if(connection != null) {
            connection.next = prev;
        }else{
            head = prev;
        }
        tail.next = curr;
    }

}
