package data;

public class Node<T>{
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T value){
        this.data = value;
        this.next = null;
        this.prev = null;
    }
}
