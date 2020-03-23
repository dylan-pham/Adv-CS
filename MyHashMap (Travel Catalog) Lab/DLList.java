public class DLList<V> {
    private Node<V> head;
    private Node<V> tail;
    private int size;

    public DLList() {
        head = new Node<V>(null);
        tail = new Node<V>(null);
        size = 0;
    }

    public void add(V data) {
        Node<V> node = new Node<V>(data);
        if (size == 0) {
            head.setNext(node);
            node.setPrev(head);
            node.setNext(tail);
            tail.setPrev(node);
        } else {
            tail.prev().setNext(node);
            node.setNext(tail);
            node.setPrev(tail.prev());
            tail.setPrev(node);
        }

        size++;
    }

    public V get(int loc) {
        Node<V> current = head.next();

        for (int i = 0; i < size; i++) {
            if (i == loc) {
                return current.get();
            }

            current = current.next();
        }

        return null;
    }

    public void remove(V data) {
        Node<V> current = head.next();

        if (data.equals(head.get())) {
            head = head.next();
        } else {
            for (int i = 0; i < size; i++) {
                if (get(i).equals(data)) {
                    current.prev().setNext(current.next());
                    size--;
                }
    
                current = current.next();
            }
        }

    }

    public String toString() {
        String s = "";
        Node<V> current = head.next();

        for (int i = 0; i < size; i++) {
            s += current.get() + "\n";

            current = current.next();
        }

        return s;
    }

    public int size() {
        return size;
    }
}