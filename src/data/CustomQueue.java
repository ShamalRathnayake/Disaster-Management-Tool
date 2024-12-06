package data;

class CustomQueue<T> {
    private CustomLinkedList<T> list;

    public CustomQueue() {
        list = new CustomLinkedList<>();
    }

    public void enqueue(T item) {
        list.insertEnd(item);
    }

    public Node<T> dequeue() {
        return list.isEmpty() ? null : list.deleteFront();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Node<T> peek() {
        return list.isEmpty() ? null : list.getFirst();
    }
}
