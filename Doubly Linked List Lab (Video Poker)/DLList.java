public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        size = 0;
    }

    public void add(E data) {
        Node<E> newNode = new Node<E>(data);

        if (size == 0) {
            head.setNext(newNode);
            tail.setPrev(newNode);
            newNode.setPrev(head);
            newNode.setNext(tail);
        } else {
            tail.prev().setNext(newNode);
            newNode.setPrev(tail.prev());
            tail.setPrev(newNode);
            newNode.setNext(tail);
        }

        size++;
    }

    public void remove(int index) {
        if (index == 0) {
            head.setNext(head.next().next());
            size--;
        } else {
            Node<E> current = head.next();
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    current.prev().setNext(current.next());
                    current.next().setPrev(current.prev());
                    size--;
                    break;
                }
    
                current = current.next();
            }
        }
    }

    public E get(int index) {
        Node<E> current = head.next();

        for (int i = 0; i < size; i++) {
            if (i == index) {
                return current.get();
            }

            current = current.next();
        }

        return null;
    }

    public void set(int index, E data) {
        Node<E> current = head.next();

        for (int i = 0; i < size; i++) {
            if (i == index) {
                current.setData(data);
                break;
            }

            current = current.next();
        }
    }

    public String toString() {
        String s = "";
        Node<E> current = head.next();

        while (current.next() != null) {
            s += current.get().toString();

            current = current.next();
        }

        return s;
    }

    public int size() {
        return size;
    }
}