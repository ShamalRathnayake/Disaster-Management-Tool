package data;

import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList<T> {
    private Node<T> head;

    public CustomLinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void insertFront(T value) {
        Node<T> newNode = new Node<T>(value);

        if (this.isEmpty()) {
            this.head = newNode;
            return;
        }

        newNode.next = this.head;
        this.head.prev = newNode;
        this.head = newNode;
    }

    public void insertAfter(T value, T nodeValue) {
        Node<T> newNode = new Node<T>(value);

        if (this.isEmpty()) {
            throw new IllegalStateException("Linked list is empty");
        }

        if (this.head.data.equals(nodeValue)) {
            newNode.next = this.head.next;
            if (this.head.next != null) {
                this.head.next.prev = newNode;
            }
            newNode.prev = this.head;
            this.head.next = newNode;
            return;
        }

        Node<T> current = this.head;
        while (current.next != null && !current.data.equals(nodeValue)) {
            current = current.next;
        }

        if (current.next == null) {
            throw new IllegalStateException("Value not found in Linked List");
        }

        newNode.next = current.next;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        newNode.prev = current;
        current.next = newNode;
    }

    public void insertEnd(T value) {
        Node<T> newNode = new Node<T>(value);

        if (this.isEmpty()) {
            this.head = newNode;
            return;
        }

        Node<T> current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        newNode.prev = current;
        current.next = newNode;
    }

    public Node<T> deleteFront() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Linked list is empty");
        }

        Node<T> temp = this.head;

        if (this.head.next != null) {
            this.head.next.prev = null;
        }

        this.head = this.head.next;

        return temp;
    }

    public Node<T> deleteNode(T nodeValue) {
        if (this.isEmpty()) {
            throw new IllegalStateException("Linked list is empty");
        }

        Node<T> current = this.head;
        while (current.next != null && !current.data.equals(nodeValue)) {
            current = current.next;
        }

        if (current.next == null && !current.data.equals(nodeValue)) {
            return null;
        }

        Node<T> temp = current;

        if (current.prev != null) {
            current.prev.next = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }

        return temp;
    }

    public Node<T> deleteEnd() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Linked list is empty");
        }

        Node<T> current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        Node<T> temp = current;

        if (current.prev != null) {
            current.prev.next = null;
        }

        return temp;
    }

    public Node<T> getFirst() {
        return this.head;
    }

    public Node<T> getNode(T nodeValue) {
        if (this.isEmpty()) {
            return null;
        }

        Node<T> current = this.head;
        while (current.next != null && !current.data.equals(nodeValue)) {
            current = current.next;
        }

        if (!current.data.equals(nodeValue)) {
            return null;
        }

        return current;
    }

    public Node<T> getLast() {
        if (this.isEmpty()) {
            return null;
        }

        Node<T> current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        return current;
    }

    public void printAll() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Linked list is empty");
        }

        Node<T> current = this.head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = this.head;

        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        return list;
    }


}
