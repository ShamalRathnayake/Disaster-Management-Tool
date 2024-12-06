package data;

public class CustomStack<T> {
    private CustomLinkedList<T> list;

    public CustomStack() {
        list = new CustomLinkedList<>();
    }

    public void push(T item) {
        list.insertFront(item);
    }

    public Node<T> pop() {
        return list.isEmpty() ? null : list.deleteFront();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Node<T> peek() {
        return list.isEmpty() ? null : list.getFirst();
    }
}
