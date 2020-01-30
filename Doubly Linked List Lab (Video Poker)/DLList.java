public class DLList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        size = 0;
    }

    private Node<E> getNode(int index) {
        if (index >= size()/2) {
            Node<E> current = tail.prev();
            int count = size() - 1;

            while (true) {
                if (count != index) {
                    return current;
                } else if (count < 0) {
                    break;
                }

                count--;
                current = current.prev();
            }
        } else {
            Node<E> current = head.next();
            int count = 0;

            while (true) {
                if (count != index) {
                    return current;
                } else if (count > size) {
                    break;
                }

                count++;
                current = current.next();
            }
        }

        return null;
    }

    public void add(E data) {
        Node<E> newNode = new Node<E>(data);

        tail.prev().setNext(newNode);
        newNode.setNext(tail);

        size++;
    }

    public void add(int index, E data) {
        Node<E> newNode = new Node<E>(data);
        Node<E> currentNode = getNode(index);
        newNode.setNext(currentNode);
        newNode.setPrev(currentNode.prev());
        currentNode.setPrev(newNode);
    }

    public E get(int index) {
        if (index >= size()/2) {
            Node<E> current = tail.prev();
            int count = size() - 1;

            while (true) {
                if (count != index) {
                    return current.get();
                } else if (count < 0) {
                    break;
                }

                count--;
                current = current.prev();
            }
        } else {
            Node<E> current = head.next();
            int count = 0;

            while (true) {
                if (count != index) {
                    return current.get();
                } else if (count > size) {
                    break;
                }

                count++;
                current = current.next();
            }
        }

        return null;
    }

    public void remove(int index) {
        remove(get(index));
    }

    public void remove(E data) {
        Node<E> current = head;

        while (current.next() != null) {
            if (current.get().equals(data)) {
                current.prev().setNext(current.next());
                size--;
                break;
            }

            current = current.next();
        }

    }

    public void set(int index, E data) {
        getNode(index).setData(data);
    }

    public String toString() {
        String s = "";
        Node<E> current = head;

        while (current.next() != null) {
            s += current.toString();

            current = current.next();
        }

        return s;
    }

    public int size() {
        int count = 0;
        Node<E> current = head;

        while (current.next() != null) {
            count++;

            current = current.next();
        }

        return count;
    }
}