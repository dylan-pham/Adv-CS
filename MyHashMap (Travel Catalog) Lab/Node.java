public class Node<V> {
    private V data;
    private Node<V> next;
    private Node<V> prev;

    public Node(V data) {
        this.data = data;
        next = null;
        prev = null;
    }

    public V get() {
        return data;
    }

    public Node<V> next() {
        return next;
    }

    public Node<V> prev() {
        return prev;
    }

    public void setNext(Node<V> next) {
        this.next = next;
    }

    public void setPrev(Node<V> prev) {
        this.prev = prev;
    }
}